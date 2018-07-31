using UnityEngine;

namespace LightControlSystem
{
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
