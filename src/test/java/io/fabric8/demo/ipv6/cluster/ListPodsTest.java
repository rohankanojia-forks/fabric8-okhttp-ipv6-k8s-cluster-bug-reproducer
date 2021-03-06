package io.fabric8.demo.ipv6.cluster;

import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ListPodsTest {
    @Test
    void listPodsTest() {
        // Given
        KubernetesClient client = new DefaultKubernetesClient();

        // When
        PodList podList = client.pods().inAnyNamespace().list();

        // Then
        assertNotNull(podList);
        assertFalse(podList.getItems().isEmpty());
    }
}
