	package com.example.demo.dao;
	
	
	
	
	import org.hibernate.Session;
	
	import org.hibernate.SessionFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Repository;
	
	import com.example.demo.dto.UserRequest;
	import com.example.demo.dto.UserResponse;
	import com.example.demo.model.User;
	
	import jakarta.persistence.Entity;
	import jakarta.persistence.EntityManager;
	import jakarta.persistence.PersistenceContext;
	import jakarta.persistence.criteria.CriteriaBuilder;
	import jakarta.persistence.criteria.CriteriaQuery;
	import jakarta.persistence.criteria.Root;
	import jakarta.transaction.Transactional;
	import jakarta.transaction.Transactional.TxType;
	
	import org.hibernate.Transaction;
	
	@Repository
	public class UserDaoimp implements UserDao{
		
		@PersistenceContext
		private EntityManager entityManager;
		
		
	
	
		@Override
		@Transactional(value = TxType.REQUIRED)
		public boolean createUser(UserRequest userRequest) {
		    try {
		        User user = User.builder()
		                .firstName(userRequest.getFirstName())
		                .lastName(userRequest.getLastName())
		                .email(userRequest.getEmail())
		                .password(userRequest.getPassword())
		                .role(userRequest.getRole())
		                .build();
		        entityManager.persist(user);
		        return true;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
	
	
	
	
		@Override
		public UserResponse getUserById(long id) {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	
	
		@Override
		public UserResponse updateUserById(UserRequest userRequest, long id) {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	
	
		@Override
		public boolean deleteUserById(long id) {
			// TODO Auto-generated method stub
			return false;
		}
	
	
	
	
		@Override
		public UserResponse login(String email, String password) {
			// TODO Auto-generated method stub
			return null;
		}
	
	
	
	
		@Override
		public Long getUserIdByEmail(String email) {
			// TODO Auto-generated method stub
			return null;
		}




		@Override
		public UserResponse getUserById(Long userId) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		@Transactional
		public void save(User user) {
		    try {
		        // Persist the User entity using EntityManager
		        entityManager.persist(user);
		    } catch (Exception e) {
		        // Log the error and handle exceptions
		        e.printStackTrace();
		    }
		}




		@Override
		public boolean createUser(User user) {
			// TODO Auto-generated method stub
			return false;
		}

	}
	
	//	@Override
	//	public UserResponse getUserById(long id) {
	//		
	//		Session session = sessionFactory.openSession();
	//		
	//		Transaction tx = session.beginTransaction();
	//		
	//		try {
	//			
	//			User user = session.get(User.class, id);
	//			
	//			return UserResponse.builder()
	//					.email(user.getEmail())
	//					.firstName(user.getFirstName())
	//					.lastName(user.getLastName())
	//					.role(user.getRole())
	//					.id(user.getId())
	//					.build();
	//			
	//			
	//			
	//		}
	//		catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//		
	//		
	//		return null;
	//	}
	//
	//	@Override
	//	public UserResponse updateUserById(UserRequest userRequest, long id) {
	//		Session session = sessionFactory.openSession();
	//		
	//		Transaction tx = session.beginTransaction();
	//		
	//		try {
	//			
	//			User user = User.builder()	
	//					.email(userRequest.getEmail())
	//					.firstName(userRequest.getFirstName())
	//					.lastName(userRequest.getLastName())
	//					.password(userRequest.getPassword())
	//					.role(userRequest.getRole())
	//					.id(id)
	//					.build();
	//			
	//			
	//			session.saveOrUpdate(user);
	//			
	//			tx.commit();
	//			
	//			user = session.get(User.class, id);
	//
	//			
	//			 return UserResponse.builder()
	//					.email(user.getEmail())
	//					.firstName(user.getFirstName())
	//					.lastName(user.getLastName())
	//					.role(user.getRole())
	//					.id(user.getId())
	//					.build();
	//			
	//		}
	//		catch(Exception e) {
	//			e.printStackTrace();
	//			
	//		}
	//		session.close();
	//		
	//		return null;
	//	}
	//
	//	@Override
	//	public boolean deleteUserById(long id) {
	//		
	//		Session session = sessionFactory.openSession();
	//		
	//		Transaction tx = session.beginTransaction();
	//		
	//		try {
	//			
	//			User user = session.get(User.class, id);
	//			
	//			session.remove(user);
	//			
	//			tx.commit();
	//			
	//			return true;
	//			
	//			
	//		}
	//		catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//		
	//		session.close();
	//		
	//		return false;
	//		
	//	}
	//
	//	@Override
	//	public UserResponse login(String email, String password) {
	//		Session session = sessionFactory.openSession();
	//		
	//		Transaction tx = session.beginTransaction();
	//		
	//		//String hql = "FROM User u WHERE u.email= :email AND u.password= :password";
	//		
	//		//String jpql = "SELECT u FROM User u WHERE u.email= :email AND u.password= :password";
	//		
	//		//String nq = "SELECT u FROM User u WHERE u.email=?1 AND u.password=?2";
	//
	//		
	//		
	//		
	//		try {
	//			
	//			/*User user =  (User) session.createQuery(hql)
	//					.setParameter("email", email)
	//					.setParameter("password", password)
	//					.getSingleResult();
	//					*/
	//			
	//			/*User user = session.createQuery(jpql, User.class)
	//					.setParameter("email", email)
	//					.setParameter("password", password)
	//					.getSingleResult(); */
	//			
	//			/*User user = session.createQuery(nq, User.class)
	//					.setParameter(1, email)
	//					.setParameter(2, password)
	//					.getSingleResult();*/
	//			
	//			CriteriaBuilder cb = session.getCriteriaBuilder();
	//			CriteriaQuery<User> cq = cb.createQuery(User.class);
	//			
	//			Root<User> user = cq.from(User.class);
	//
	//		    cq.select(user)
	//		      .where(cb.and(
	//		          cb.equal(user.get("email"), email),
	//		          cb.equal(user.get("password"), password)));
	//		    
	//		    User result = session.createQuery(cq).getSingleResult();
	//			
	//			
	//			
	//			tx.commit();
	//			
	//			System.out.println(result.toString());
	//			
	//			return UserResponse.builder()
	//					.email(result.getEmail())
	//					.firstName(result.getFirstName())
	//					.lastName(result.getLastName())
	//					.role(result.getRole())
	//					.id(result.getId())
	//					.build();
	//			
	//			/*
	//			 *return UserResponse.builder()
	//					.email(user.getEmail())
	//					.firstName(user.getFirstName())
	//					.lastName(user.getLastName())
	//					.role(user.getRole())
	//					.id(user.getId())
	//					.build();
	//			 */
	//			
	//			
	//		}
	//		catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//		session.close();
	//		return null;
	//	}
	//
	//}
