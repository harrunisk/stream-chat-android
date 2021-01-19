package com.getstream.sdk.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.getstream.sdk.chat.databinding.StreamItemCommandBinding
import com.getstream.sdk.chat.view.messageinput.MessageInputStyle
import io.getstream.chat.android.client.models.Command

class CommandsAdapter(
    private val style: MessageInputStyle,
    private val onCommandSelected: (Command) -> Unit
) : ListAdapter<Command, CommandViewHolder>(
    object : DiffUtil.ItemCallback<Command>() {
        override fun areItemsTheSame(oldItem: Command, newItem: Command): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Command, newItem: Command): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommandViewHolder =
        CommandViewHolder(
            StreamItemCommandBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            style,
            onCommandSelected
        )

    override fun onBindViewHolder(holder: CommandViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class CommandViewHolder(
    private val binding: StreamItemCommandBinding,
    private val style: MessageInputStyle,
    private val onCommandClicked: (Command) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(command: Command) {
        binding.tvCommand.text = command.name
        binding.tvArg.text = command.args
        binding.tvDes.text = command.description
        style.inputBackgroundText.let {
            it.apply(binding.tvCommand)
            it.apply(binding.tvDes)
            it.apply(binding.tvArg)
        }
        binding.root.setOnClickListener { onCommandClicked(command) }
    }
}
