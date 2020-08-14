package com.topov.accessorycompatibility.receiver;

import com.topov.accessorycompatibility.assembler.HardwareAssemblerImpl;
import com.topov.accessorycompatibility.hardware.sources.CpuSource;
import com.topov.accessorycompatibility.receiver.ekatalog.EkatalogSpecificationReceiver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@MockBeans({@MockBean(EkatalogSpecificationReceiver.class), @MockBean(HardwareAssemblerImpl.class)})
@ActiveProfiles("test")
class HadrwareReceiverInvokerTest {
    private final HadrwareReceiverInvoker invoker;
    private final SpecificationReceiver receiver;

    @Autowired
    public HadrwareReceiverInvokerTest(HadrwareReceiverInvoker invoker, SpecificationReceiver receiver) {
        this.invoker = invoker;
        this.receiver = receiver;
    }

    @Test
    public void whenNotSupportedSourceUrl_ThenThrowRuntimeException() {
        final String url = "http://notsupported/cpu";
        final CpuSource mockSource = mock(CpuSource.class);
        when(mockSource.getSourceUrl()).thenReturn(url);

        assertThrows(RuntimeException.class, () -> invoker.invokeReceiver(mockSource).join());
        verify(receiver, times(0)).receiveCpu(url);
    }

    @Test
    public void whenSupportedSourceUrl_ThenCallReceiveCpu() {
        final String url = "https://ek.ua/cpu";
        final CpuSource mockSource = new CpuSource(url);

        when(receiver.supports(url)).thenReturn(true);

        invoker.invokeReceiver(mockSource).join();

        verify(receiver, times(1)).receiveCpu(url);
    }
}
