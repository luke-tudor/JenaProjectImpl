using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent access card.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class AccessCard : MonoBehaviour
    {
        void Start()
        {
            StatementChanger _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "person", LCSEndpoint.PREFIX + "hasAccessCard");
            _changer.changeObject(new ConcreteObjectChanger(s => s.changeObject(s.getModel().getResource(LCSEndpoint.PREFIX + "accessCard").asResource())));
        }
    }
}