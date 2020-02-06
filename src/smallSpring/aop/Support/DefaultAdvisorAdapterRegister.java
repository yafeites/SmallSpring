package smallSpring.aop.Support;

import smallSpring.aop.advice.Interface.Advice;
import smallSpring.aop.advisor.Advisor;
import smallSpring.aop.methodinterceptor.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

public class DefaultAdvisorAdapterRegister implements AdvisorAdapterRegistry {
    private  List<AdvisorAdapter>adapters=new ArrayList<AdvisorAdapter>();

    public DefaultAdvisorAdapterRegister() {
        registerAdvisorAdapter(new MethodBeforeAdviceAdapter());
    }

    @Override
    public MethodInterceptor[] getInterceptors(Advisor advisor) {
        List<MethodInterceptor> interceptors =new ArrayList<>();
        Advice advice=advisor.getAdvice();
        for(AdvisorAdapter adapter:this.adapters)
        {
                  if(adapter.supportAdvice(advice))
            {
                interceptors.add(adapter.getInterceptor(advisor));
            }
        }
        return interceptors.toArray(new MethodInterceptor[interceptors.size()]);
    }

    @Override
    public void registerAdvisorAdapter(AdvisorAdapter adapter) {
        adapters.add(adapter);
    }
}
