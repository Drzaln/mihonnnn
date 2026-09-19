package eu.kanade.presentation.reader

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import tachiyomi.i18n.MR
import tachiyomi.presentation.core.i18n.stringResource

@Composable
fun SourceReactionPromptDialog(
    chapterName: String,
    onDismissRequest: () -> Unit,
    onUpvote: () -> Unit,
    onDontAskAgain: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(stringResource(MR.strings.source_reaction_prompt_title)) },
        text = { Text(stringResource(MR.strings.source_reaction_prompt_message, chapterName)) },
        confirmButton = {
            TextButton(onClick = onUpvote) {
                Text(stringResource(MR.strings.action_upvote))
            }
        },
        dismissButton = {
            Row {
                TextButton(onClick = onDismissRequest) {
                    Text(stringResource(MR.strings.action_not_now))
                }
                TextButton(onClick = onDontAskAgain) {
                    Text(stringResource(MR.strings.action_dont_ask_again))
                }
            }
        },
    )
}
