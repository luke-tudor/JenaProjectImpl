using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent access room.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class AccessRoom : MonoBehaviour
    {
        private Collider _collider;
        private bool _isOpen;

        void Start()
        {
            _collider = GetComponent<BoxCollider>();
            LCSEndpoint.lcs.registerStatementListener(new ConcreteListener(LCSEndpoint.PREFIX + "accessRoom", LCSEndpoint.PREFIX + "isOpen", s =>
            {
                _isOpen = s.getBoolean();
            }));
        }

        void Update()
        {
            _collider.isTrigger = _isOpen;
        }
    }
}
