using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    /*
     * Class to represent the player's name which can be modified from within the unity editor.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class PlayerName : MonoBehaviour
    {
        public string _playerName;

        void Start()
        {
            StatementChanger _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "person", LCSEndpoint.PREFIX + "hasName");
            _changer.changeObject(new ConcreteObjectChanger(s => s.changeObject(_playerName)));
        }
    }
}