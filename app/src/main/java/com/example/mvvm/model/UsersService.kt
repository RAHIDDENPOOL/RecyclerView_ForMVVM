package com.example.mvvm.model

import com.example.mvvm.UserNotFoundException
import com.github.javafaker.Faker
import java.util.*

typealias UsersListener = (users: List<User>) -> Unit

class UsersService {

    private var users = mutableListOf<User>()

    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        users = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size]
            )
        }.toMutableList()
    }

    fun getUsers(): List<User> {
        return users
    }

    fun getById(id: Long): UserDetails {
        val user = users.firstOrNull { it.id == id } ?: throw UserNotFoundException()
        return UserDetails(
            user = user,
            details = Faker.instance().lorem().paragraphs(3).joinToString("\n\n")
        )

    }

    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }

    companion object {
        private val IMAGES = mutableListOf(
            "",//todo
            "https://sun9-west.userapi.com/sun9-2/s/v1/ig2/HPn-BeFzeser4e8cMcoN2SYE-NAMSlCPskY-hVB3Vbo6o9stDOZ2_LRdVeN7OvzdpbeL0L-2tLnlIJDCrwNo2g1K.jpg?size=1024x768&quality=95&type=album",
            "https://sun9-east.userapi.com/sun9-75/s/v1/if1/PDClYZ2Z38M03bU43a2mVtEKxl2y0ugb7NUJnI_ZXMi2qY-XMPpyR7zP9cmaEcK8TSIjps_a.jpg?size=900x632&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-50/s/v1/ig2/_rsjB6-jyJQfTlLh_qfF5YAPXgsVqkHsqc7quVoZ96C9QINDZnUNTk4STu_6-iQ3L-lC17Z6OVyYaLKTiB_YaBou.jpg?size=688x1080&quality=96&type=album",
            "https://sun9-east.userapi.com/sun9-31/s/v1/ig2/AM6a3z6ZoNaE43zDfTOvr7ZODhIGjmSq21aoFWb1KoMRDhs9yqZ1LCXJzntML08YRyl6f6yp9_EcyH1saei9ILyN.jpg?size=749x530&quality=96&type=album",
            "",//todo
            "https://sun9-east.userapi.com/sun9-21/s/v1/ig2/BwEehoaCpLDvMouEUw-sOAKhwuk-kXBfov1k-b2lR35B8TT4hzIrpLHa1ltltxrzZ6DguZRtJeOMxk0ob3DpE1L9.jpg?size=2160x2160&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-52/s/v1/ig2/ldyjbU2qpOkpua4ByWTAAsTmNGRxrtpXqxTRzdK_TqRUZFW7ukDl4Ng2tIChY7OlbnN-q0_Qx2QwU9xFHYbmVyUu.jpg?size=736x736&quality=95&type=album",
            "https://sun9-west.userapi.com/sun9-62/s/v1/ig2/O3PCcVjQPcCUk1Ht9AAbSDbreiL1vP8tS0xTpFaFRxv7Vn6_cXX2x1biOxCVl05dlbB8xX6A_PELGk55DURKsBMl.jpg?size=750x750&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-7/s/v1/if1/eQar4pnEi13rURoSWmRkh36stAqxY0nNW8kixuPyIcarD2baeA_ePjJNRsoFXtubtohnTd6M.jpg?size=677x960&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-53/s/v1/ig2/7oXh482hoIpmaam5UbwReJ-1FV-4SE7zf_5EYKe_7TAw2K8gmbBDFryY3ZK07T_AeVCOb6q8x9BCE7UlmUgRsWcq.jpg?size=900x900&quality=96&type=album",
            "https://sun9-west.userapi.com/sun9-51/s/v1/ig2/3fsbD97HxYb78OpljrmOJQgcmdo6ObePPUkuKJLK_RcSWrH2eHqmZ7B2Sy6yCMrmZTVqBA_Udz_oZsFk2GCWBytO.jpg?size=1024x1280&quality=96&type=album",
            ""//todo
        )
    }
}