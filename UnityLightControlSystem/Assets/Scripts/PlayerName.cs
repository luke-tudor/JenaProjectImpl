using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class PlayerName : MonoBehaviour
    {
        public string _playerName;

        void Start()
        {
            StatementChanger _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "person", LCSEndpoint.PREFIX + "hasName");
            _changer.changeLiteralObject(new ConcreteLiteralObjectChanger(s => s.changeObject(_playerName)));
        }
    }
}