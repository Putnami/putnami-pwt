/**
 * This file is part of pwt.
 *
 * pwt is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * pwt is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with pwt. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package fr.putnami.pwt.plugin.code.client.token.evaluator;

import com.google.common.base.Preconditions;

import fr.putnami.pwt.plugin.code.client.token.CharacterScanner;
import fr.putnami.pwt.plugin.code.client.token.SimpleToken;
import fr.putnami.pwt.plugin.code.client.token.Token;
import fr.putnami.pwt.plugin.code.client.token.TokenContent;
import fr.putnami.pwt.plugin.code.client.token.TokenEvaluator;
import fr.putnami.pwt.plugin.code.client.util.CharacterUtil;

public class PatternTokenEvaluator implements TokenEvaluator {

	protected final char[] startSequence;
	protected final char[] endSequence;
	protected final TokenContent tokenContent;
	protected final char escapeCharacter;
	protected final boolean breaksOnEOL;
	protected final boolean breaksOnEOF;

	public PatternTokenEvaluator(String startSequence, String endSequence, TokenContent tokenContent,
		char escapeCharacter, boolean breaksOnEOL, boolean breaksOnEOF) {
		Preconditions.checkArgument(startSequence != null, "Start sequence can not be null.");
		Preconditions.checkArgument(startSequence.length() > 0, "Start sequence can not be empty.");
		Preconditions.checkArgument(endSequence != null || breaksOnEOL || breaksOnEOF,
			"Either endOnEOF/EOL or end sequence must be defined");

		this.startSequence = startSequence.toCharArray();
		this.endSequence = endSequence == null ? new char[0] : endSequence.toCharArray();
		this.tokenContent = tokenContent;
		this.escapeCharacter = escapeCharacter;
		this.breaksOnEOL = breaksOnEOL;
		this.breaksOnEOF = breaksOnEOF;
	}

	@Override
	public Token<?> evaluate(CharacterScanner charScanner) {
		StringBuilder resultText = new StringBuilder();
		int charScanned = charScanner.read();
		if (charScanned == this.startSequence[0]) {
			resultText.append((char) charScanned);
			if (this.sequenceDetected(charScanner, this.startSequence, resultText, false)) {
				if (this.endSequenceDetected(charScanner, resultText)) {
					return new SimpleToken<TokenContent>(charScanner.getMark(), resultText.toString(),
						this.tokenContent);
				}
			}
		} else {
			charScanner.unread();
		}
		return SimpleToken.UNDEFINED_TOKEN;
	}

	protected boolean endSequenceDetected(CharacterScanner scanner, StringBuilder matchingText) {
		int c;
		while ((c = scanner.read()) != CharacterScanner.EOF) {
			matchingText.append((char) c);
			if (c == this.escapeCharacter) {
				int nextChar = scanner.read();
				if (nextChar == CharacterScanner.EOF) {
					break;
				}
				matchingText.append((char) nextChar);
			} else if (this.endSequence.length > 0 && c == this.endSequence[0]) {
				if (this.sequenceDetected(scanner, this.endSequence, matchingText, this.breaksOnEOF)) {
					return true;
				}
			} else if (this.breaksOnEOL) {
				// Check for end of line since it can be used to terminate the pattern.
				for (char[] element : CharacterUtil.END_OF_LINE_DELIMITERS) {
					if (c == element[0]
						&& this.sequenceDetected(scanner, element, matchingText, this.breaksOnEOF)) {
						return true;
					}
				}
			}
		}
		if (this.breaksOnEOF) {
			return true;
		}
		scanner.unread();
		for (int j = 0; j < matchingText.length(); j++) {
			scanner.unread();
		}
		return false;
	}

	protected boolean sequenceDetected(CharacterScanner scanner, char[] sequence,
		StringBuilder matchingText, boolean eofAllowed) {
		for (int i = 1; i < sequence.length; i++) {
			int c = scanner.read();
			if (c == CharacterScanner.EOF && eofAllowed) {
				return true;
			} else if (c != sequence[i]) {
				scanner.unread();
				for (int j = i - 1; j > 0; j--) {
					scanner.unread();
				}
				matchingText.delete(matchingText.length() - i + 1, matchingText.length());
				return false;
			}
			matchingText.append((char) c);
		}

		return true;
	}

}
