package smallSpring.aop.classfilter;

import smallSpring.aop.PointCut.TruePointcut;

public class TrueClassFilter implements  ClassFilter{
    static  final TrueClassFilter INSTANCE=new TrueClassFilter();
    private TrueClassFilter()
    {

    }
    @Override
    public boolean matches(Class c) {
        return true;
    }
}
