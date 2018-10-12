using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent the fridge object.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class SodaFridge : MonoBehaviour
    {
        private Camera _mainCamera;
        private string _objectName;
        private StatementChanger _changer;

        void Start()
        {
            _mainCamera = Camera.main;
            _objectName = "SodaFridge";
            _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "sodaFridge", LCSEndpoint.PREFIX + "hasSodaBottlesRemaining");
        }

        void Update()
        {
            if (Input.GetMouseButtonDown(0))
            {
                if (MouseRaycastOnFridge())
                {
                    _changer.changeObject(new ConcreteObjectChanger(s =>
                    {
                        s.changeLiteralObject(s.getInt() - 1);
                    }));
                }
            }
        }

        private bool MouseRaycastOnFridge()
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
