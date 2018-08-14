using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class SodaRefillButton : MonoBehaviour
    {
        private Camera _mainCamera;
        private string _objectName;
        private StatementChanger _changer;

        void Start()
        {
            _mainCamera = Camera.main;
            _objectName = "SodaRefillButton";
            _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "sodaFridge", LCSEndpoint.PREFIX + "hasSodaBottlesRemaining");
        }

        void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                if (MouseRaycastOnButton())
                {
                    _changer.changeObject(new ConcreteObjectChanger(s =>
                    {
                        s.changeLiteralObject(5);
                    }));
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
