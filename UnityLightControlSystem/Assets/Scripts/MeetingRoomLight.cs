using com.hp.hpl.jena.rdf.model;
using UnityEngine;
using jenaInterface;

public class MeetingRoomLight : MonoBehaviour
{
    private Light _light;
    private bool _isOn = true;
    private float _intensity = 5;

    internal class LightListener : Listener
    {
        private readonly MeetingRoomLight _outer;
        private readonly string _resourceName;
        private readonly string _propertyName;

        public LightListener(MeetingRoomLight outer, string resourceName, string propertyName)
        {
            _outer = outer;
            _resourceName = resourceName;
            _propertyName = propertyName;
        }

        protected override void apply(Statement s)
        {
            _outer._isOn = s.getBoolean();
        }

        protected override string propertyName()
        {
            return _propertyName;
        }

        protected override string resourceName()
        {
            return _resourceName;
        }
    }

    /*internal class LightIntensityListener : Listener
    {
        private readonly MeetingRoomLight _outer;
        private readonly string _resourceName;
        private readonly string _propertyName;

        public LightIntensityListener(MeetingRoomLight outer, string resourceName, string propertyName)
        {
            _outer = outer;
            _resourceName = resourceName;
            _propertyName = propertyName;
        }

        protected override void apply(Statement s)
        {
            int val = s.getInt();
            Debug.Log(val);
            _outer._intensity = val / 20.0f;
        }

        protected override string propertyName()
        {
            return _propertyName;
        }

        protected override string resourceName()
        {
            return _resourceName;
        }
    }*/

    void Start()
    {
        _light = GetComponent<Light>();
        LCSEndpoint.lcs.registerStatementListener(new LightListener(this, LCSEndpoint.PREFIX + "meetingRoomLight", LCSEndpoint.PREFIX + "isDeviceTurnedOn"));
        //LCSEndpoint.lcs.registerStatementListener(new LightIntensityListener(this, LCSEndpoint.PREFIX + "meetingRoomLight", LCSEndpoint.PREFIX + "lightHasIntensity"));
        /*ThreadStart childref = new ThreadStart(() =>
        {
            for (;;)
            {
                Thread.Sleep(200);
                _isOn = LCSEndpoint.lcs.ShouldLightBeOn();
            }
        });
        Thread childThread = new Thread(childref);
        childThread.Start();*/
    }

    void Update()
    {
        _light.enabled = _isOn;
        _light.intensity = _intensity;
    }
}
