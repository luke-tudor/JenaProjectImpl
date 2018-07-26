using UnityEngine;

namespace LightControlSystem
{
    public class OfficeLight : MonoBehaviour
    {
        private Light _light;
        private float _intensity;

        void Start()
        {
            _light = GetComponent<Light>();
            _intensity = _light.intensity;
            LCSEndpoint.lcs.registerStatementListener(new ConcreteListener(LCSEndpoint.PREFIX + "meetingRoomLight", LCSEndpoint.PREFIX + "lightHasIntensity", s =>
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
}

