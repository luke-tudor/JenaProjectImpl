using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class AccessCard : MonoBehaviour
    {
        void Start()
        {
            StatementChanger _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "person", LCSEndpoint.PREFIX + "hasAccessCard");
            _changer.changeObject(new ConcreteObjectChanger(s => s.changeObject(s.getModel().getResource(LCSEndpoint.PREFIX + "accessCard").asResource())));
        }
    }
}