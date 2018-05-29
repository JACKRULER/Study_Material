package com.quintype.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import java.awt.*;
import java.awt.datatransfer.*;

public class SetContentsToClipBoard extends OpenBrowser {
    public static void setText(String text)
    {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
    }

}
