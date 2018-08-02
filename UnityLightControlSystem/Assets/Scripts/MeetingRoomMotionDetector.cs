using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class MeetingRoomMotionDetector : MonoBehaviour
    {
        private StatementChanger _changer;

        void Start()
        {
            _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "meetingRoomMotionDetector", LCSEndpoint.PREFIX + "detectMotion");
        }

        private void OnTriggerEnter(Collider other)
        {
            _changer.changeObject(new ConcreteObjectChanger(s => s.changeLiteralObject(true)));
        }

        private void OnTriggerExit(Collider other)
        {
            _changer.changeObject(new ConcreteObjectChanger(s => s.changeLiteralObject(false)));
        }
    }
}