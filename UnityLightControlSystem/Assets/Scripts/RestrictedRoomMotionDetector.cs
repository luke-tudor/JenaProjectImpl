using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent the restricted room motion detector.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class RestrictedRoomMotionDetector : MonoBehaviour
    {
        private StatementChanger _changer;

        void Start()
        {
            _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "restrictedRoomMotionDetector", LCSEndpoint.PREFIX + "detectMotion");
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