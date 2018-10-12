﻿using UnityEngine;
using UnityEngine.UI;

namespace LightControlSystem
{
    /*
     * Class to represent the alert message for the restricted room.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class RestrictedRoomAlert : MonoBehaviour
    {
        private Text _text;
        private string _textString;

        void Start()
        {
            _text = GetComponent<Text>();
            _textString = "";
            LCSEndpoint.lcs.registerStatementListener(new ConcreteListener(LCSEndpoint.PREFIX + "alert", LCSEndpoint.PREFIX + "hasMessage", s =>
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