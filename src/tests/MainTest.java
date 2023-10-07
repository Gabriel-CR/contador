package tests;

import controller.Contagem;
import exceptions.ParametrosInvalidosException;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MainTest {
    private Contagem contagem;

    @Test
    public void testParametroUmMaiorParametroDois() {
        assertThrows(ParametrosInvalidosException.class, () -> {
            Contagem.contar(20, 10);
        });
    }

    @Test
    public void testParametrosValidos() {
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream customOut = new PrintStream(outputStream);

            System.setOut(customOut);

            Contagem.contar(1, 3);

            String consoleOutput = outputStream.toString();

            assertTrue(consoleOutput.contains("Imprimindo o número 1\nImprimindo o número 2"));
        } catch (ParametrosInvalidosException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(originalOut);
        }
    }
}
