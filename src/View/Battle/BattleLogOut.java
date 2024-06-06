package View.Battle;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Log output to display battle text on battle screen.
 *
 * @author James
 */
public class BattleLogOut extends OutputStream {
    /**
     * Text area for battle log
     */
    private final JTextArea myBattleLogArea;

    /**
     * String builder used to concatenate streamed text.
     */
    private final StringBuilder sb = new StringBuilder();

    /**
     * Constructor the text area of the battle log output.
     * @param theTA Text area used for battle log output.
     */
    public BattleLogOut(final JTextArea theTA) {
        myBattleLogArea = theTA;
    }

    /**
     * Writes a byte to the output stream and accumulates characters until a complete line is received.
     * @param theByte The byte to write.
     */
    @Override
    public void write(int theByte) throws IOException {
        // Convert byte to character
        char byteConvert = (char) theByte;
        sb.append(byteConvert);

        // If the character is a newline character, it indicates the end of a line
        if (byteConvert == '\n') {
            SwingUtilities.invokeLater(() -> {
                myBattleLogArea.append(sb.toString());
                // Set caret position to the end of the text area
                myBattleLogArea.setCaretPosition(myBattleLogArea.getText().length());
                sb.setLength(0);
                int lineCount = myBattleLogArea.getLineCount();
                if (lineCount > 6) {
                    try {
                        // Calculate the start and end offset of the first line
                        int startOffset = myBattleLogArea.getLineStartOffset(0);
                        int endOffset = myBattleLogArea.getLineEndOffset(0);

                        // Remove the first line of text
                        myBattleLogArea.replaceRange("", startOffset, endOffset);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }

                    // After removing the first line, set caret position to the end of the text area
                    myBattleLogArea.setCaretPosition(myBattleLogArea.getText().length());
                }
            });
        }
    }
}
