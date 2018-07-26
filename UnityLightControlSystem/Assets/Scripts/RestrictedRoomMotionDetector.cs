using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class RestrictedRoomMotionDetector : MonoBehaviour
    {
        private StatementChanger _changer;

        void Start()
        {
            _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "restrictedRoomMotionDetector", LCSEndpoint.PREFIX + "detectMotion");
        }

        private void OnTriggerEnter(Collider other)
        {
            _changer.changeLiteralObject(new ConcreteLiteralObjectChanger(s => s.changeLiteralObject(true)));
        }

        private void OnTriggerExit(Collider other)
        {
            _changer.changeLiteralObject(new ConcreteLiteralObjectChanger(s => s.changeLiteralObject(false)));
        }
    }
}