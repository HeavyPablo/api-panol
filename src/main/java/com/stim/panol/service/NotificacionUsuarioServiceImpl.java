package com.stim.panol.service;

import com.stim.panol.model.NotificacionUsuario;
import com.stim.panol.model.Usuario;
import com.stim.panol.repository.NotificacionUsuarioRepository;
import com.stim.panol.service.iservice.NotificacionUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificacionUsuarioServiceImpl implements NotificacionUsuarioService {

    @Autowired
    NotificacionUsuarioRepository notificacionUsuarioRepository;

    @Override
    public List<NotificacionUsuario> findAll() {
        return notificacionUsuarioRepository.findAll();
    }

    @Override
    public Optional<NotificacionUsuario> findById(int id) {
        return notificacionUsuarioRepository.findById(id);
    }

    @Override
    public Optional<List<NotificacionUsuario>> findByUsuario(Usuario usuario) {
        return notificacionUsuarioRepository.findByUsuarioOrderByFechaDesc(usuario);
    }

    @Override
    public NotificacionUsuario save(NotificacionUsuario notificacionUsuario) {
        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionCrearSolicitud(Usuario usuario, int idSolicitud) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Solicitud " + idSolicitud + " enviada, vaya a pañol para recibir sus materiales");
        notificacionUsuario.setOperacion("crear");
        notificacionUsuario.setEstado("solicitud");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionValidarSolicitud(Usuario usuario, int idSolicitud) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Solicitud " + idSolicitud + " aceptada");
        notificacionUsuario.setOperacion("validar");
        notificacionUsuario.setEstado("solicitud");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionRecibirSolicitud(Usuario usuario, int idSolicitud) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Ya tienes tus materiales de la solicitud " + idSolicitud);
        notificacionUsuario.setOperacion("recibir");
        notificacionUsuario.setEstado("solicitud");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionDevolverSolicitud(Usuario usuario, int idSolicitud) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Has devulto los materiales de la solicitud " + idSolicitud);
        notificacionUsuario.setOperacion("devolver");
        notificacionUsuario.setEstado("solicitud");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionDeshabilitarSolicitud(Usuario usuario, int idSolicitud) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Tu solicitud " + idSolicitud + "ha sido cancelada :(");
        notificacionUsuario.setOperacion("deshabilitar");
        notificacionUsuario.setEstado("solicitud");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionActivarUsuario(Usuario usuario) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Bienvenido devuelta " + getNombreUsuario(usuario) + "!");
        notificacionUsuario.setOperacion("activar");
        notificacionUsuario.setEstado("usuario");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionMorosoUsuario(Usuario usuario) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Tienes una deuda. Devuelve los materiales a pañol Duoc");
        notificacionUsuario.setOperacion("moroso");
        notificacionUsuario.setEstado("usuario");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    public NotificacionUsuario getNotificacionBloquearUsuario(Usuario usuario) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        NotificacionUsuario notificacionUsuario = new NotificacionUsuario();

        notificacionUsuario.setNotificacion("Has sido bloqueado");
        notificacionUsuario.setOperacion("moroso");
        notificacionUsuario.setEstado("usuario");
        notificacionUsuario.setFecha(dateFormat.format(date));
        notificacionUsuario.setUsuario(usuario);

        return notificacionUsuarioRepository.save(notificacionUsuario);
    }

    private String getNombreUsuario(Usuario usuario) {
        String nombreUsuario = "";

        switch (usuario.getPerfil()) {
            case "ALUMNO":
                nombreUsuario = usuario.getAlumno().getNombre() + " " + usuario.getAlumno().getApellidoPaterno();
                break;
            case "DOCENTE":
                nombreUsuario = usuario.getDocente().getNombre() + " " + usuario.getDocente().getApellidoPaterno();
                break;
            default:
                break;
        }
        return nombreUsuario;
    }
}
