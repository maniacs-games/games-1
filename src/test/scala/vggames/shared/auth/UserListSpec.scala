package vggames.shared.auth

import org.specs2.mutable.Specification
import org.specs2.mock.Mockito
import org.scribe.model.Token
import org.specs2.specification.Scope

class UserListSpec extends Specification with Mockito {
  "Find by" should {
    "returns None when there isnt uses" in new FindAByContext {
      users findBy("other-provider-name", "user-name") must_== None
    }

    "found a user by provider and user name" in new FindAByContext {
      users += "provider-name" -> user
      users findBy("provider-name", "user-name") must_== Some(user)
    }

    "found a user when there are more than one at same provider" in new FindAByContext {
      val otherUser = User("other-user-name", mock[Token])
      users += "provider-name" -> user
      users += "provider-name" -> otherUser
      users findBy("provider-name", "user-name") must_== Some(user)
    }

    "found a user when there are more than one at diferents provider" in new FindAByContext {
      val otherUser = User("user-name", mock[Token])
      users += "provider-name" -> user
      users += "other-provider-name" -> otherUser
      users findBy("other-provider-name", "user-name") must_== Some(otherUser)
    }

    "returns None when search by absent user" in new FindAByContext {
      val otherUser = User("other-user-name", mock[Token])
      users += "provider-name" -> user
      users += "other-provider-name" -> otherUser
      users findBy("other-provider-name", "user-name") must_== None
    }
  }
}

trait FindAByContext extends Scope with Mockito {
  val users = UserList()
  val user = User("user-name", mock[Token])
}