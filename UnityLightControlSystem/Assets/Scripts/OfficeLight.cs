using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent office light which is affected by outside sunlight.
     * Author: Luke Tudor
     * Date: October 2018
     */
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
                _intensity = val / 20.0f;
            }));
        }

        void Update()
        {
            _light.intensity = _intensity;
        }
    }
}

