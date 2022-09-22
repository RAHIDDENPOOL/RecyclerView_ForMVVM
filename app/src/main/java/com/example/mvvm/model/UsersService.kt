package com.example.mvvm.model

import com.github.javafaker.Faker
import java.util.*

typealias UsersListener = (users: List<User>) -> Unit

class UsersService {
    private var users: MutableList<User> = mutableListOf<User>()

    private val listeners: MutableList<UsersListener> = mutableListOf<UsersListener>()

    init {
        val faker: Faker = Faker.instance()
        IMAGES.shuffle()
        val generatedUsers: List<User> = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size]
            )
        }
    }

    fun getUsers(): List<User> {
        return users
    }

    fun deleteUser(user: User) {
        val indexToDelete: Int = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex: Int = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex: Int = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener(listener: UsersListener) {
        listeners.remove(listener)
        listener.invoke(users)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }

    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://sun9-west.userapi.com/sun9-16/s/v1/if1/6Xt4jO_JvQDt2Tsp8pUQC5UxLCJAHIzals94SH8PtKTPkMTTZUs-QoXhgVFE2aZGDJMeYjoL.jpg?size=536x517&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-2/s/v1/ig2/HPn-BeFzeser4e8cMcoN2SYE-NAMSlCPskY-hVB3Vbo6o9stDOZ2_LRdVeN7OvzdpbeL0L-2tLnlIJDCrwNo2g1K.jpg?size=1024x768&quality=95&type=album",
            "https://sun9-east.userapi.com/sun9-75/s/v1/if1/PDClYZ2Z38M03bU43a2mVtEKxl2y0ugb7NUJnI_ZXMi2qY-XMPpyR7zP9cmaEcK8TSIjps_a.jpg?size=900x632&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-50/s/v1/ig2/_rsjB6-jyJQfTlLh_qfF5YAPXgsVqkHsqc7quVoZ96C9QINDZnUNTk4STu_6-iQ3L-lC17Z6OVyYaLKTiB_YaBou.jpg?size=688x1080&quality=96&type=album",
            "https://sun9-east.userapi.com/sun9-31/s/v1/ig2/AM6a3z6ZoNaE43zDfTOvr7ZODhIGjmSq21aoFWb1KoMRDhs9yqZ1LCXJzntML08YRyl6f6yp9_EcyH1saei9ILyN.jpg?size=749x530&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-40/s/v1/ig2/oD8V8zIZfSpIsZ5ddw7B1l13WUnKHmspp3gM4XzQEuiVju2UcNLA99aKJUDfmJLVFGSW2-bR_DktlgkL7hkBpBZG.jpg?size=1200x1200&quality=95&type=album",
            "https://sun9-east.userapi.com/sun9-21/s/v1/ig2/BwEehoaCpLDvMouEUw-sOAKhwuk-kXBfov1k-b2lR35B8TT4hzIrpLHa1ltltxrzZ6DguZRtJeOMxk0ob3DpE1L9.jpg?size=2160x2160&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-52/s/v1/ig2/ldyjbU2qpOkpua4ByWTAAsTmNGRxrtpXqxTRzdK_TqRUZFW7ukDl4Ng2tIChY7OlbnN-q0_Qx2QwU9xFHYbmVyUu.jpg?size=736x736&quality=95&type=album",
            "https://sun9-west.userapi.com/sun9-62/s/v1/ig2/O3PCcVjQPcCUk1Ht9AAbSDbreiL1vP8tS0xTpFaFRxv7Vn6_cXX2x1biOxCVl05dlbB8xX6A_PELGk55DURKsBMl.jpg?size=750x750&quality=96&type=album"
        )
    }
}