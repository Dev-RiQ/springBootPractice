import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customer-ex");
//        System.out.println("emf = " + emf);
        EntityManager em = emf.createEntityManager();
//        System.out.println("em = " + em);
        EntityTransaction tx = em.getTransaction();
//        System.out.println("tx = " + tx);
        tx.begin();
        try{
//            Customer customer1 = new Customer("park");
//            Customer customer2 = new Customer("lee");
//            Customer customer3 = new Customer("kim");
//            em.persist(customer1);
//            em.persist(customer2);
//            em.persist(customer3);
//            Customer customer1 = em.find(Customer.class, 1L);
//            System.out.println("customer1 = " + customer1);
//            Customer customer2 = em.find(Customer.class, 1L);
//            System.out.println("customer2 = " + customer2);
//            System.out.println(customer1 == customer2);
//            Student stu1 = new Student("김씨","1학년");
//            Student stu2 = new Student("이씨","2학년");
//            Student stu3 = new Student("박씨","3학년");
//            Student stu4 = new Student("제갈씨","3학년");
//            Major m1 = new Major("컴싸","소프트엔지니어링");
//            Major m2 = new Major("컴싸2","소프트엔지니어링2");
//            em.persist(stu1);
//            em.persist(stu2);
//            em.persist(stu3);
//            em.persist(stu4);
//            em.persist(m2);
//            stu1.setMajor(m1);
//            stu2.setMajor(m1);
//            stu3.setMajor(m1);
//            stu4.setMajor(m2);
//            Locker l1 = new Locker(1001);
//            Locker l2 = new Locker(1002);
//            Locker l3 = new Locker(1003);
//            em.persist(l1);
//            em.persist(l2);
//            em.persist(l3);
//            stu1.setLocker(l1);
//            stu2.setLocker(l2);
//            stu3.setLocker(l3);
//            Student student = em.find(Student.class, 1L);
//            System.out.println("student = " + student);
            Locker locker = em.find(Locker.class, 1L);
            System.out.println("locker = " + locker);
            Major major = em.find(Major.class, 1L);
            System.out.println("major = " + major);
            System.out.println("student = " + locker.getStudent());
            System.out.println("student = " + major.getStudents());
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }finally {
            em.close();
            emf.close();
        }
    }
}
