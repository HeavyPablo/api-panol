package com.stim.panol.service;

import com.stim.panol.model.ImagenProducto;
import com.stim.panol.repository.ImagenProductoRepository;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImagenProductoServiceImpl implements ImagenProductoService{

    @Autowired
    private ImagenProductoRepository imagenProductoRepository;

    @Override
    public Optional<ImagenProducto> findById(int id) {
        return imagenProductoRepository.findById(id);
    }

    @Override
    public ImagenProducto storeFile(MultipartFile file) throws Exception {
        String nombre = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            byte[] byteImage = compressImage(ImageIO.read(file.getInputStream()), file.getContentType(), 0.05f);
            ImagenProducto imagenProducto = new ImagenProducto(byteImage, nombre, file.getContentType());

            return imagenProductoRepository.save(imagenProducto);
        } catch (IOException e) {
            throw new Exception("Error al obtener la imagen " + nombre, e);
        }
    }

    @Override
    public ImagenProducto getFile(int id) {
        return imagenProductoRepository.findById(id).get();
    }

    @Override
    public void deleteFile(int id) {
        imagenProductoRepository.deleteById(id);
    }

    // Comprimir imagen
    private byte[] compressImage(BufferedImage bi, String mimeType, float quality) throws IOException {

        ImageWriter writer = ImageIO.getImageWritersByMIMEType(mimeType).next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();

        if (!mimeType.equalsIgnoreCase("image/png") && !mimeType.equalsIgnoreCase("image/bmp")) {
            iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            iwp.setCompressionQuality(quality);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new java.io.BufferedOutputStream(baos);
        ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
        writer.setOutput(ios);

        IIOImage optimizedImage = new IIOImage(bi, null, null);
        writer.write(null, optimizedImage, iwp);
        writer.dispose();
        baos.flush();

        return baos.toByteArray();
    }
}
