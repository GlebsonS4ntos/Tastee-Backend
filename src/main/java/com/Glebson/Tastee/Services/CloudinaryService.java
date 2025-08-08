package com.Glebson.Tastee.Services;

import com.Glebson.Tastee.Domain.Image;
import com.Glebson.Tastee.Domain.Post;
import com.Glebson.Tastee.Exceptions.ImgException;
import com.Glebson.Tastee.Exceptions.NotFoundException;
import com.Glebson.Tastee.Repositories.ImageRepository;
import com.Glebson.Tastee.Repositories.PostRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
    private final ImageRepository repository;
    private final Cloudinary cloudinary;
    private final PostRepository postRepository;

    public CloudinaryService(ImageRepository repository,PostRepository postRepository) {
        this.postRepository = postRepository;
        this.repository = repository;
        Dotenv dotenv = Dotenv.load();
        this.cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    }

    public Image uploadImage(MultipartFile img, Long postId){
        try {
            Post p =postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Receita não encontrada."));

            List<String> tipos = List.of(
                "image/png", "image/jpeg", "image/jpg", "image/webp"
            );

           if (!tipos.contains(img.getContentType())) {
               throw new ImgException("Verifique se e tipo da imagem é png, jpg, jpeg ou webp.");
           }

           System.out.println(p.getImage());
            if (p.getImage() != null) {
                Image oldImage = p.getImage();
                p.setImage(null);
                oldImage.setPost(null);

                postRepository.save(p);

                deleteImage(oldImage);
            }

            Map uploadResult = cloudinary.uploader().upload(img.getBytes(), ObjectUtils.emptyMap());

            Image i = new Image();
            i.setUrlImg((String) uploadResult.get("url"));
            i.setImgIdCloudnary((String) uploadResult.get("public_id"));

            i.setPost(p);
            p.setImage(i);

            postRepository.save(p);

            return p.getImage();
        }catch (Exception e){
            throw new ImgException(e.getMessage());
        }
    }

    public void deleteImage(Image img){
        try {
            Map result = cloudinary.uploader().destroy(img.getImgIdCloudnary(), ObjectUtils.emptyMap());

            System.out.println(img.getImgIdCloudnary());

            if (!result.get("result").equals("ok")) {
                throw new ImgException("Não foi possível deletar a imagem do Cloudinary.");
            }

            repository.delete(img);
        }catch (Exception e){
            throw new ImgException(e.getMessage());
        }
    }
}
