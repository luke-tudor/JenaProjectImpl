using UnityEngine;

namespace LightControlSystem
{
    public class OfficeLight : MonoBehaviour
    {
        private Light _light;
        private float _intensity = 5;

        void Start()
        {
            _light = GetComponent<Light>();
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

