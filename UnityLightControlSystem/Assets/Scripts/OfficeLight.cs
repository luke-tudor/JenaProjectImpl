using com.hp.hpl.jena.rdf.model;
using UnityEngine;
using jenaInterface;

public class OfficeLight : MonoBehaviour
{
    private Light _light;
    private float _intensity = 5;
    public delegate void StatementConsumer(Statement s);

    internal class LightIntensityListener : Listener
    {
        private readonly OfficeLight _outer;
        private readonly string _resourceName;
        private readonly string _propertyName;
        private StatementConsumer _callback;

        public LightIntensityListener(OfficeLight outer, string resourceName, string propertyName, StatementConsumer callback)
        {
            _outer = outer;
            _resourceName = resourceName;
            _propertyName = propertyName;
            _callback = callback;
        }

        protected override void apply(Statement s)
        {
            _callback(s);
            //int val = s.getInt();
            //Debug.Log(val);
            //_outer._intensity = val / 20.0f;
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

    void Start()
    {
        _light = GetComponent<Light>();
        LCSEndpoint.lcs.registerStatementListener(new LightIntensityListener(this, LCSEndpoint.PREFIX + "meetingRoomLight", LCSEndpoint.PREFIX + "lightHasIntensity", s =>
        {
            int val = s.getInt();
            Debug.Log(val);
            _intensity = val / 20.0f;
        }));
    }

    void Update()
    {
        _light.intensity = _intensity;
    }
}
