using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent meeting room light which is controlled by the meeting room motion detector.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class MeetingRoomLight : MonoBehaviour
    {
        private Light _light;
        private bool _isOn = true;

        void Start()
        {
            _light = GetComponent<Light>();
            LCSEndpoint.lcs.registerStatementListener(new ConcreteListener(LCSEndpoint.PREFIX + "meetingRoomLight", LCSEndpoint.PREFIX + "isDeviceTurnedOn", s =>
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
