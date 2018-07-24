using System;
using com.hp.hpl.jena.rdf.model;
using jenaInterface;
using UnityEngine;

public class MotionDetector : MonoBehaviour
{

    private StatementChanger _changer;
    private BooleanChanger _trueChanger;
    private BooleanChanger _falseChanger;

    internal class BooleanChanger : LiteralObjectChanger
    {
        private readonly bool _b;

        public BooleanChanger(bool b)
        {
            _b = b;
        }

        public void apply(Statement s)
        {
            s.changeLiteralObject(_b);
        }
    }

    void Start()
    {
        _changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "meetingRoomMotionDetector", LCSEndpoint.PREFIX + "detectMotion");
        _trueChanger = new BooleanChanger(true);
        _falseChanger = new BooleanChanger(false);
    }

    private void OnTriggerEnter(Collider other)
    {
        _changer.changeLiteralObject(_trueChanger);
        //LCSEndpoint.lcs.IsMotionDetected(true);
    }

    private void OnTriggerExit(Collider other)
    {
        _changer.changeLiteralObject(_falseChanger);
        //LCSEndpoint.lcs.IsMotionDetected(false);
    }
}
