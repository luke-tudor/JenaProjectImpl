using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent custom room light (light controlled with a switch).
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class CustomRoomLight : MonoBehaviour
    {
        private Light _light;
        private bool _isOn = true;

        void Start()
        {
            _light = GetComponent<Light>();
            LCSEndpoint.lcs.registerStatementListener(new ConcreteListener(LCSEndpoint.PREFIX + "customRoomLight", LCSEndpoint.PREFIX + "isDeviceTurnedOn", s =>
            {
                _isOn = s.getBoolean();
            }));
        }

        void Update()
        {
            _light.enabled = _isOn;
        }
    }
}
