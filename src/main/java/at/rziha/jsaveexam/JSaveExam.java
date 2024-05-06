/*
 * Copyright (c) 2024, Alexander Rziha
 *
 * Licensed under the BSD 3-Clause License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package at.rziha.jsaveexam;

import at.rziha.jsaveexam.border.RoundedBorder;
import at.rziha.jsaveexam.webpage.BlackedOutWindow;
import at.rziha.jsaveexam.webpage.WebPageLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JSaveExam {
    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            new JSaveExam(null);
            return;
        }

        String url = null;

        for (String arg : args) {
            if (arg.startsWith("--url="))
            {
                url = arg.substring("--url=".length());
            }
        }

        new JSaveExam(url);
    }

    private GraphicsDevice defaultGraphicsDefault = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    private List<GraphicsDevice> notMainDisplay = new ArrayList<>();

    public JSaveExam(String url) {
        if (url == null || url.isEmpty())
            url = "https://html5test.co/";

        int test = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length;

        if (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices().length > 1) {
            for (GraphicsDevice graphicsDevice : GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()) {
                if (graphicsDevice != defaultGraphicsDefault)
                    notMainDisplay.add(graphicsDevice);
            }
        }

        String finalUrl = url;
        SwingUtilities.invokeLater(() -> {
            WebPageLoader webPageLoader = new WebPageLoader(finalUrl);
            webPageLoader.setVisible(true);
            addCloseButton(webPageLoader);
            defaultGraphicsDefault.setFullScreenWindow(webPageLoader);
        });

        if (!notMainDisplay.isEmpty())
        {
            notMainDisplay.forEach(d -> {
                SwingUtilities.invokeLater(() -> {
                    BlackedOutWindow blackedOutWindow = new BlackedOutWindow();
                    Rectangle bounds = d.getDefaultConfiguration().getBounds();
                    blackedOutWindow.setLocationByPlatform(true);
                    blackedOutWindow.setLocation(bounds.x, bounds.y);
                    d.setFullScreenWindow(blackedOutWindow);

                    addCloseButton(blackedOutWindow);

                    blackedOutWindow.setVisible(true);
                });
            });
        }
    }

    private static void addCloseButton(JFrame frame) {
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> System.exit(0));
        closeButton.setMargin(new Insets(5, 5, 5, 5));
        closeButton.setBackground(new Color(220, 220, 220));
        closeButton.setBorder(new RoundedBorder(5));

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topBar.setBackground(new Color(220, 220, 220));
        topBar.add(closeButton);
        frame.add(topBar, BorderLayout.NORTH);
    }
}