package com.team22.soundary.feature.search.data.repository

import com.team22.soundary.feature.search.data.model.FriendEntity

class FriendRepository {

    companion object {
        private var instance: FriendRepository? = null

        fun getInstance(): FriendRepository {
            if (instance == null) {
                instance = FriendRepository()
            }
            return instance!!
        }
    }

    private val allFriends = mutableListOf(
        FriendEntity(
            id = extractIdFromEmail("gogoKim@example.com"),
            name = "김고고",
            email = "gogoKim@example.com",
            statusMessage = "안녕하세요!",
            favoriteGenres = listOf("Ballad", "Pop"),
            status = "requested"
        ),
        FriendEntity(
            id = extractIdFromEmail("parkKim@example.com"),
            name = "박고고",
            email = "parkKim@example.com",
            statusMessage = "음악 좋아요",
            favoriteGenres = listOf("Jazz", "Blues"),
            status = "requested"
        ),
        FriendEntity(
            id = extractIdFromEmail("nyamnyam@example.com"),
            name = "김남남",
            email = "nyamnyam@example.com",
            statusMessage = "행복한 하루!",
            favoriteGenres = listOf("R&B", "Soul"),
            status = "accepted"
        ),
        FriendEntity(
            id = extractIdFromEmail("nyamnyam22@example.com"),
            name = "이남남",
            email = "nyamnyam22@example.com",
            statusMessage = "즐거운 음악!",
            favoriteGenres = listOf("J-pop", "Anime"),
            status = "accepted"
        ),
        FriendEntity(
            id = extractIdFromEmail("kookooyong@example.com"),
            name = "쿠키즈용",
            email = "kookooyong@example.com",
            statusMessage = "Let's enjoy music!",
            favoriteGenres = listOf("Pop", "Dance"),
            status = "pending"
        ),
        FriendEntity(
            id = extractIdFromEmail("kookoo@example.com"),
            name = "쿠키즈",
            email = "kookoo@example.com",
            statusMessage = "음악은 삶의 일부",
            favoriteGenres = listOf("Hip-hop", "Pop"),
            status = "pending"
        )
    )

    // 이메일에서 '@' 앞부분을 추출하여 ID로 사용
    private fun extractIdFromEmail(email: String): String {
        return email.substringBefore("@")
    }

    // 나에게 친구 요청을 보낸 친구 목록 반환 (NewFriends)
    fun getNewFriends(): List<FriendEntity> {
        return allFriends.filter { it.status == "requested" }
    }

    // 내가 친구 요청을 보낸 친구 목록 반환 (PendingFriends)
    fun getPendingFriends(): List<FriendEntity> {
        return allFriends.filter { it.status == "pending" }
    }

    // 친구 상태인 친구 목록 반환 (My Friends)
    fun getMyFriends(): List<FriendEntity> {
        return allFriends.filter { it.status == "accepted" }
    }

    // 친구 ID로 프로필 정보 가져오기
    fun getFriendById(friendId: String): FriendEntity? {
        return allFriends.find { it.id == friendId }
    }
    // 친구의 상태를 업데이트하는 메서드 수정
    fun updateFriendStatus(friendId: String, newStatus: String) {
        val friend = allFriends.find { it.id == friendId }
        friend?.status = newStatus
    }

    // 친구를 리스트에서 제거하는 메서드 수정 -> mutableListof로 변경해야함 했음 !!
    fun removeFriend(friendId: String) {
        allFriends.removeAll { it.id == friendId }
    }
    // 친구를 추가하는 메서드 추가
    fun addFriend(friendId: String): Boolean {
        // 이미 친구인지 확인
        val existingFriend = allFriends.find { it.id == friendId }
        return if (existingFriend == null) {
            // 새로운 친구 정보 생성
            val newFriend = fetchFriendDetails(friendId)
            newFriend?.let {
                it.status = "accepted" // 친구 추가 시 상태를 'accepted'로 설정
                allFriends.add(it)
                true // 성공적으로 추가됨
            } ?: false // 친구 정보 가져오기 실패
        } else {
            // 이미 존재하는 경우
            false
        }
    }

    // 친구의 상세 정보를 가져오는 메서드 (예시로 간단히 구현)
    private fun fetchFriendDetails(friendId: String): FriendEntity? {
        return FriendEntity(
            id = friendId,
            name = "새 친구",
            email = "$friendId@example.com",
            statusMessage = "안녕하세요!",
            favoriteGenres = listOf("Pop", "Rock"),
            status = "accepted"
        )
    }
}
