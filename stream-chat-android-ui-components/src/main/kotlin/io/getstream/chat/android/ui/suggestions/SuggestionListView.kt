package io.getstream.chat.android.ui.suggestions

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ConcatAdapter
import io.getstream.chat.android.client.models.Command
import io.getstream.chat.android.client.models.User
import io.getstream.chat.android.ui.databinding.StreamUiSuggestionListViewBinding

public class SuggestionListView : FrameLayout {

    private val binding: StreamUiSuggestionListViewBinding = LayoutInflater.from(context).let {
        StreamUiSuggestionListViewBinding.inflate(it, this)
    }
    private val mentionsAdapter: MentionsAdapter = MentionsAdapter { listener?.onMentionClick(it) }
    private val commandsAdapter: CommandsAdapter = CommandsAdapter { listener?.onCommandClick(it) }

    private var listener: OnSuggestionClickListener? = null

    public constructor(context: Context) : super(context)

    public constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    public constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        binding.suggestionsRecyclerView.apply {
            itemAnimator = null
            adapter = ConcatAdapter(mentionsAdapter, commandsAdapter)
        }
    }

    public fun setSuggestions(suggestions: Suggestions) {
        binding.suggestionsCardView.isVisible = true
        when (suggestions) {
            is Suggestions.MentionSuggestions -> {
                if (suggestions.users.isEmpty()) {
                    hideSuggestionList()
                } else {
                    mentionsAdapter.submitList(suggestions.users.take(MAX_SUGGESTIONS))
                    binding.commandsTitleTextView.isVisible = false
                }
            }
            is Suggestions.CommandSuggestions -> {
                if (suggestions.commands.isEmpty()) {
                    hideSuggestionList()
                } else {
                    commandsAdapter.submitList(suggestions.commands.take(MAX_SUGGESTIONS))
                    binding.commandsTitleTextView.isVisible = true
                }
            }
        }
    }

    public fun isSuggestionListVisible(): Boolean {
        return binding.suggestionsCardView.isVisible
    }

    public fun setOnSuggestionClickListener(listener: OnSuggestionClickListener) {
        this.listener = listener
    }

    public fun hideSuggestionList() {
        if (binding.suggestionsCardView.isVisible) {
            commandsAdapter.submitList(emptyList())
            mentionsAdapter.submitList(emptyList())
            binding.suggestionsCardView.isVisible = false
        }
    }

    public interface OnSuggestionClickListener {
        public fun onMentionClick(user: User)

        public fun onCommandClick(command: Command)
    }

    public sealed class Suggestions {
        public data class MentionSuggestions(val users: List<User>) : Suggestions()
        public data class CommandSuggestions(val commands: List<Command>) : Suggestions()
    }

    private companion object {
        private const val MAX_SUGGESTIONS: Int = 4
    }
}
