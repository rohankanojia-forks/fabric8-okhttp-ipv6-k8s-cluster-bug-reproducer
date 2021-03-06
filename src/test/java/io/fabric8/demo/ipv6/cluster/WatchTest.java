package io.fabric8.demo.ipv6.cluster;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.Watcher;
import io.fabric8.kubernetes.client.WatcherException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WatchTest {
    @Test
    void testWatch() throws InterruptedException {
        // Given
        KubernetesClient client = new DefaultKubernetesClient();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // When
        client.pods().inAnyNamespace().watch(new Watcher<Pod>() {
            @Override
            public void eventReceived(Action action, Pod pod) {
                System.out.println(action.name() + " " + pod.getMetadata().getNamespace() + "/" + pod.getMetadata().getName());
                countDownLatch.countDown();
            }

            @Override
            public void onClose(WatcherException e) { }
        });

        // Then
        assertTrue(countDownLatch.await(1, TimeUnit.SECONDS));
    }
}
