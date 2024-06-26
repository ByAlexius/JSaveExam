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

import javax.swing.*;
import java.awt.*;

public class BlackedOutWindow extends JFrame {
    public BlackedOutWindow() {
        setTitle("Blacked Out");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setAutoRequestFocus(true);

        getContentPane().setBackground(Color.BLACK);
    }
}
