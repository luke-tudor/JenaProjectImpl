using jenaInterface;
using UnityEngine;

namespace LightControlSystem
{
    public class LightDetector : MonoBehaviour
    {
        public Light sunlight;

        private int sign;
        private StatementChanger changer;
        private int interval;
        private int intensity;

        void Start()
        {
            sign = -1;
            changer = LCSEndpoint.lcs.makeStatementChanger(LCSEndpoint.PREFIX + "lightDetector", LCSEndpoint.PREFIX + "detectLightIntensity");
            interval = 50;
            intensity = 100;
        }

        void Update()
        {
            if (interval <= 0)
            {
                if (intensity <= 0)
                {
                    sign = 1;
                }
                else if (intensity >= 100)
                {
                    sign = -1;
                }
                intensity += sign;
                sunlight.intensity = intensity / 100.0f;
                //Debug.Log(intensity);
                changer.changeObject(new ConcreteObjectChanger(s => s.changeLiteralObject(intensity)));
                interval = 50;
            }
            interval--;
        }
    }
}