package javaexercise.spring4.aspectoriented4;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class EncoreableIntroducer
{
   //@DeclareParents(value="javaexercise.spring4.aspectoriented4.Performance+", defaultImpl=DefaultEncoreable.class)
    public static Encoreable encoreable;
}
