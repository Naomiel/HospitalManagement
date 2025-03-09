package com.qucoon.hospitalmanagement.system;

import com.qucoon.hospitalmanagement.model.entity.Appointment;
import com.qucoon.hospitalmanagement.model.entity.Patient;
import com.qucoon.hospitalmanagement.model.response.AppointmentResponse;
import com.qucoon.hospitalmanagement.model.response.PatientResponse;
import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrikaBeanMapper extends ConfigurableMapper implements ApplicationContextAware {
    private MapperFactory mapperFactory;
    private ApplicationContext applicationContext;

    public OrikaBeanMapper() {
        super(false);
    }

    private void configureMappers(MapperFactory mapperFactory) {
        mapperFactory.classMap(Patient.class, PatientResponse.class)
                .byDefault()
                .register();

        mapperFactory.classMap(Appointment.class, AppointmentResponse.class)
                .byDefault()
                .register();
    }

    @Override
    protected void configure(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        configureMappers(mapperFactory);

        addAllSpringBeans();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureFactoryBuilder(final DefaultMapperFactory.Builder factoryBuilder) {
        // Nothing to configure for now
    }

    /**
     * Constructs and registers a {@link ClassMapBuilder} into the {@link MapperFactory} using a {@link Mapper}.
     *
     * @param mapper
     */
    public void addMapper(Mapper<?, ?> mapper) {
        mapperFactory.classMap(mapper.getAType(), mapper.getBType())
                .byDefault()
                .customize((Mapper) mapper)
                .register();
    }

    /**
     * Registers a {@link Converter} into the {@link ConverterFactory}.
     *
     * @param converter
     */
    public void addConverter(Converter<?, ?> converter) {
        mapperFactory.getConverterFactory().registerConverter(converter);
    }

    /**
     * Scans the application context and registers all Mappers and Converters found in it.
     */
    private void addAllSpringBeans() {
        Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        for (Mapper mapper : mappers.values()) {
            addMapper(mapper);
        }
        Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        for (Converter converter : converters.values()) {
            addConverter(converter);
        }
    }


    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        init();
    }

    public <T> T mapBean(Object source, T targetClass) {
        this.map(source, targetClass);
        return targetClass;
    }

}