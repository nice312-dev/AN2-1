package ru.netology.adapter

import androidx.recyclerview.widget.RecyclerView
import ru.netology.R
import ru.netology.databinding.CardPostBinding
import ru.netology.dto.Post

class PostViewHolder (
    private val binding: CardPostBinding,
    private val listener: Listener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post : Post) {
        binding.apply {
            txtTitle.text = post.title
            txtSubtitle.text = post.subTitle
            txtContent.text = post.content
            txtLikes.text = formatNumber(post.likes)
            txtShares.text = formatNumber(post.shares)
            txtViews.text = formatNumber(post.views)

            imgLikes.setImageResource(
                if(post.hasAutoLike) R.drawable.ic_baseline_favorite_24
                else R.drawable.ic_baseline_favorite_border_24
            )

            imgLikes.setOnClickListener {
                listener.onLike(post)
            }

            imgShares.setOnClickListener {
                listener.onShare(post)
            }

            imgViews.setOnClickListener {
                listener.onView(post)
            }
        }
    }
}

fun formatNumber(number: Int): String {
    return when {
        number >= 1_000_000 -> roundNumber(number / 1_000_000.0) + "M"
        number >= 1_000 -> roundNumber(number / 1_000.0) + "K"
        else -> number.toString()
    }
}

fun roundNumber(number: Double): String {
    val value = number.toString().take(3)

    return when {
        value.endsWith(".") -> value.take(2)
        value.endsWith(".0") -> value.take(1)
        else -> value
    }
}