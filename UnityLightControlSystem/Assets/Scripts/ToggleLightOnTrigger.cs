using UnityEngine;

/*
 * A legacy class used by previous scenes to trigger lights turning on and off by walking into a room without Jena.
 * Author: Luke Tudor
 * Date: October 2018
 */
public class ToggleLightOnTrigger : MonoBehaviour {

    public Light _light;

    private void OnTriggerExit(Collider other)
    {
        _light.enabled = false;
    }

    private void OnTriggerEnter(Collider other)
    {
        _light.enabled = true;
    }
}
