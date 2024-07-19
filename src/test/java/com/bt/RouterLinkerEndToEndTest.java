package com.bt;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RouterLinkerEndToEndTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void main_ApplicationRun_PrintsConnections() {
        // Act
        Main.main(new String[]{});

        // Assert
        String output = outContent.toString();
        assertTrue(output.contains("---------- Connections ----------"), "Output should contain 'Connections:' but was: " + output);
        assertTrue(output.contains("Lancaster Brewery <-> Loughborough University"));
        assertTrue(output.contains("Lancaster Castle <-> Loughborough University"));
    }
}
