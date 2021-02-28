package ru.netology.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.dto.Post

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun shareById(id: Long)
    fun viewById(id: Long)
}

class PostRepositoryInMemoryImpl : PostRepository  {
    private var posts = listOf(
        Post(
            id = 0,
            likes = 10,
            shares = 997,
            views = 5,
            hasAutoLike = false,
            title = "Нетология. Университет интернет-профессий",
            subTitle = "21 мая в 18:36",
            content = "Привет! Это новая Нетология. Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению.  Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb"
        )
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map {
            if(it.id != id) it
            else it.copy(
                hasAutoLike = !it.hasAutoLike,
                likes = it.likes + 1 * (if (it.hasAutoLike) -1 else 1)
            )
        }

        data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map {
            if(it.id != id) it
            else it.copy(shares = it.shares + 1)
        }
        data.value = posts
    }

    override fun viewById(id: Long) {
        posts = posts.map {
            if(it.id != id) it else it.copy(views = it.views + 1)
        }

        data.value = posts
    }
}