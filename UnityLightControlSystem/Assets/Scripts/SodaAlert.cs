using UnityEngine;
using UnityEngine.UI;

namespace LightControlSystem
{
    public class SodaAlert : MonoBehaviour
    {
        private Text _text;
        private string _textString;

        void Start()
        {
            _text = GetComponent<Text>();
            _textString = "";
            LCSEndpoint.lcs.registerStatementListener(new ConcreteListener(LCSEndpoint.PREFIX + "alert", LCSEndpoint.PREFIX + "hasFridgeMessage", s =>
            {
                _textString = s.getString();
            }));
        }

        void Update()
        {
            _text.text = _textString;
        }
    }
}