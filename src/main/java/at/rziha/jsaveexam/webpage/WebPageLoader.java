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
package at.rziha.jsaveexam.webpage;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;

public class WebPageLoader extends JFrame {

    public WebPageLoader(String url) {
        setTitle("Web Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setAutoRequestFocus(true);

        JFXPanel fxPanel = new JFXPanel();

        Platform.runLater(() -> {
            WebView webView = new WebView();
            webView.getEngine().load(url);

            Scene scene = new Scene(webView);
            fxPanel.setScene(scene);
        });

        add(fxPanel);

        Platform.runLater(() -> {
            pack();
        });
    }
}
