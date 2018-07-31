using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class LightSwitch : MonoBehaviour
    {
        private bool _lightOn;
        private Camera _mainCamera;
        private string _objectName;
        private StatementChanger _changer;

        void Start()
        {
            _lightOn = false;
            _mainCamera = Camera.main;
            _objectName = "LightSwitch";
            _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "lightSwitch", LCSEndpoint.PREFIX + "isDeviceTurnedOn");
        }

        void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                if (MouseRaycastOnButton())
                {
                    _lightOn = !_lightOn;
                    _changer.changeLiteralObject(new ConcreteLiteralObjectChanger(s => s.changeLiteralObject(_lightOn)));
                }
            }
        }

        private bool MouseRaycastOnButton()
        {
            RaycastHit raycastHit;
            if (Physics.Raycast(_mainCamera.ScreenPointToRay(Input.mousePosition), out raycastHit))
            {
                return raycastHit.transform.name.Equals(_objectName);
            }
            return false;
        }
    }
}
