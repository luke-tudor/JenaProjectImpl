using UnityEngine;

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

    private void OnTriggerStay(Collider other)
    {

    }

    // Update is called once per frame
    void Update () {

    }
}
