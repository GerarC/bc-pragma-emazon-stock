package com.emazon.stock.configuration;

import com.emazon.stock.adapters.driven.jpa.entity.BrandEntity;
import com.emazon.stock.adapters.driven.jpa.entity.CategoryEntity;
import com.emazon.stock.adapters.driven.jpa.entity.ProductEntity;
import com.emazon.stock.adapters.driven.jpa.persistence.BrandRepository;
import com.emazon.stock.adapters.driven.jpa.persistence.CategoryRepository;
import com.emazon.stock.adapters.driven.jpa.persistence.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner loadData(CategoryRepository categoryRepository, BrandRepository brandRepository, ProductRepository productRepository) {
        return args -> {
            if(!productRepository.findAll().isEmpty()) return;

            CategoryEntity food = new CategoryEntity(null, "food", "Delicious things for your daily sustenance", new HashSet<>());
            CategoryEntity pets = new CategoryEntity(null, "pets", "For your adorable highstorm-sheltering creatures", new HashSet<>());
            CategoryEntity exercise = new CategoryEntity(null, "exercise", "Get those Shardplate gains", new HashSet<>());
            CategoryEntity clothing = new CategoryEntity(null, "clothing", "Cosmere fashion that will make even a Mistborn look twice", new HashSet<>());
            CategoryEntity weapons = new CategoryEntity(null, "weapons", "Sharpen your skills with the finest Allomantic-approved weaponry", new HashSet<>());
            CategoryEntity armor = new CategoryEntity(null, "armor", "Protection for all Knights Radiant and beyond", new HashSet<>());
            CategoryEntity homeGoods = new CategoryEntity(null, "home goods", "Decorate your chasm cave with style", new HashSet<>());
            CategoryEntity travel = new CategoryEntity(null, "travel", "Travel the Cosmere in comfort and class", new HashSet<>());
            CategoryEntity books = new CategoryEntity(null, "books", "Expand your knowledge with these world-hopping stories", new HashSet<>());
            CategoryEntity potions = new CategoryEntity(null, "potions", "A wide selection of Breaths and more for your Awakening needs", new HashSet<>());

            categoryRepository.saveAll(List.of(food, pets, exercise, clothing, weapons, armor, homeGoods, travel, books, potions));

            BrandEntity kelsierCorp = new BrandEntity(null, "Kelsier Corp", "Surviving by any means necessary, with a touch of style.", null);
            BrandEntity hoidIndustries = new BrandEntity(null, "Hoid Industries", "Making mischief across worlds since before you were born.", null);
            BrandEntity shardFit = new BrandEntity(null, "ShardFit", "Strength training for Shardbearers and Mistborn alike.", null);
            BrandEntity scadrialSnacks = new BrandEntity(null, "Scadrial Snacks", "Lerasium-infused goodies and Atium-flavored treats.", null);
            BrandEntity stormlightSurplus = new BrandEntity(null, "Stormlight Surplus", "Everything you need to survive a highstorm with flair.", null);
            BrandEntity theNightwatcher = new BrandEntity(null, "The Nightwatcher", "Wishes and boons are our business. Side effects may apply.", null);
            BrandEntity honorsHeralds = new BrandEntity(null, "Honor's Heralds", "Timeless fashion for the Herald in your life.", null);
            BrandEntity mistbornWare = new BrandEntity(null, "MistbornWare", "For all your Allomantic needs, whether you're burning metals or not.", null);
            BrandEntity worldhopperExpress = new BrandEntity(null, "Worldhopper Express", "Travel across the Cosmere without missing a beat.", null);
            BrandEntity nightbloodGear = new BrandEntity(null, "Nightblood Gear", "Destroy evil in style with our latest products.", null);

            brandRepository.saveAll(List.of(kelsierCorp, hoidIndustries, shardFit, scadrialSnacks, stormlightSurplus, theNightwatcher, honorsHeralds, mistbornWare, worldhopperExpress, nightbloodGear));

            productRepository.saveAll(List.of(
                    new ProductEntity(null, "Lerasium Flakes", "A crunchy breakfast cereal made with bits of Lerasium.", BigDecimal.valueOf(29.99), 100L, Set.of(food), scadrialSnacks),
                    new ProductEntity(null, "Stormlight Energy Drink", "Stay hydrated during highstorms!", BigDecimal.valueOf(3.99), 500L, Set.of(food), stormlightSurplus),
                    new ProductEntity(null, "Shardplate Bench Press", "Get stronger with each rep!", BigDecimal.valueOf(499.99), 20L, Set.of(exercise), shardFit),
                    new ProductEntity(null, "Chasmfiend Protein Powder", "Boost your gains with chasmfiend protein!", BigDecimal.valueOf(59.99), 75L, Set.of(exercise), shardFit),
                    new ProductEntity(null, "Vin’s Cloak", "Stay stylish in the mists with this fashionable cloak.", BigDecimal.valueOf(199.99), 30L, Set.of(clothing), honorsHeralds),
                    new ProductEntity(null, "Szeth’s Nightgown", "For a restful sleep after a hard day of soulcasting.", BigDecimal.valueOf(39.99), 200L, Set.of(clothing), honorsHeralds),
                    new ProductEntity(null, "Shardblade Replica", "For practicing your swordsmanship without the risk of dismemberment.", BigDecimal.valueOf(349.99), 50L, Set.of(weapons), mistbornWare),
                    new ProductEntity(null, "Koloss Sword", "Wield the might of a Koloss with this oversized blade.", BigDecimal.valueOf(599.99), 10L, Set.of(weapons), mistbornWare),
                    new ProductEntity(null, "Shardplate Armor", "The ultimate protection for all Radiants.", BigDecimal.valueOf(9999.99), 5L, Set.of(armor), stormlightSurplus),
                    new ProductEntity(null, "Mistcloak", "Stealth and style combined in one iconic garment.", BigDecimal.valueOf(249.99), 50L, Set.of(clothing, armor), honorsHeralds),

                    new ProductEntity(null, "Chasmfiend Egg Holder", "Display your exotic chasmfiend eggs in style.", BigDecimal.valueOf(89.99), 30L, Set.of(homeGoods), worldhopperExpress),
                    new ProductEntity(null, "Soulcast Rock Lamp", "Illuminate your home with rocks that were once food.", BigDecimal.valueOf(49.99), 100L, Set.of(homeGoods), stormlightSurplus),
                    new ProductEntity(null, "Wit's Sarcastic Dictionary", "Learn to insult like a master with this witty tome.", BigDecimal.valueOf(19.99), 200L, Set.of(books), hoidIndustries),
                    new ProductEntity(null, "Hoid’s Traveling Kit", "Everything a worldhopper needs for a quick getaway.", BigDecimal.valueOf(79.99), 75L, Set.of(travel), worldhopperExpress),
                    new ProductEntity(null, "Scadrialian Cookbook", "Learn to make Allomantic meals with this fine selection of recipes.", BigDecimal.valueOf(24.99), 150L, Set.of(books, food), scadrialSnacks),
                    new ProductEntity(null, "Nightblood’s Self-Help Guide", "A fun, quirky take on how to 'destroy evil.'", BigDecimal.valueOf(14.99), 500L, Set.of(books), nightbloodGear),
                    new ProductEntity(null, "Breath Infusion Potions", "Get a few extra Breaths to awaken objects.", BigDecimal.valueOf(89.99), 50L, Set.of(potions), theNightwatcher),
                    new ProductEntity(null, "Elantris City Tour", "Explore the ruins of Elantris with an expert guide.", BigDecimal.valueOf(499.99), 10L, Set.of(travel), worldhopperExpress),
                    new ProductEntity(null, "Kelsier’s Survivor Bandana", "Channel your inner rebel with this iconic piece of gear.", BigDecimal.valueOf(9.99), 250L, Set.of(clothing), kelsierCorp),
                    new ProductEntity(null, "Radiant Alethi Shield", "Defend yourself like a true Alethi warrior.", BigDecimal.valueOf(399.99), 40L, Set.of(armor), stormlightSurplus),

                    new ProductEntity(null, "Highstorm Survival Kit", "Everything you need to survive the storm.", BigDecimal.valueOf(149.99), 80L, Set.of(travel), stormlightSurplus),
                    new ProductEntity(null, "Lerasium Lollipops", "Tasty and infused with a little extra power.", BigDecimal.valueOf(7.99), 400L, Set.of(food), scadrialSnacks),
                    new ProductEntity(null, "Awakened Rope", "A handy rope with a mind of its own.", BigDecimal.valueOf(69.99), 150L, Set.of(potions), nightbloodGear),
                    new ProductEntity(null, "Rosharan Umbrella", "Stay dry during a highstorm with this sturdy umbrella.", BigDecimal.valueOf(29.99), 150L, Set.of(homeGoods, travel), stormlightSurplus),
                    new ProductEntity(null, "Elend's Regal Outfit", "A fashionable choice for your next political rebellion.", BigDecimal.valueOf(299.99), 20L, Set.of(clothing), honorsHeralds),
                    new ProductEntity(null, "Dalinar’s Oathbringer", "A replica of the famous Shardblade.", BigDecimal.valueOf(899.99), 10L, Set.of(weapons), mistbornWare),
                    new ProductEntity(null, "Hoid's Wit Enhancer", "For all your sarcasm and banter needs.", BigDecimal.valueOf(19.99), 300L, Set.of(books), hoidIndustries),
                    new ProductEntity(null, "Scadrial Survival Knife", "Essential for any Mistborn on the run.", BigDecimal.valueOf(49.99), 100L, Set.of(weapons), kelsierCorp),
                    new ProductEntity(null, "Nightblood Backpack", "Carries your things and destroys evil.", BigDecimal.valueOf(99.99), 150L, Set.of(homeGoods), nightbloodGear),
                    new ProductEntity(null, "Radiant Running Shoes", "Perfect for running from Voidbringers.", BigDecimal.valueOf(119.99), 200L, Set.of(exercise), shardFit),

                    new ProductEntity(null, "Shardbow", "A high-powered bow fit for a Shardbearer.", BigDecimal.valueOf(599.99), 25L, Set.of(weapons), mistbornWare),
                    new ProductEntity(null, "Kholin Royal Mantle", "Feel like a king with this luxurious mantle.", BigDecimal.valueOf(199.99), 50L, Set.of(clothing), honorsHeralds),
                    new ProductEntity(null, "Elantrian Essence", "Glow like an Elantrian with this potion.", BigDecimal.valueOf(49.99), 100L, Set.of(potions), theNightwatcher),
                    new ProductEntity(null, "Lifebinding Elixir", "Bound your soul to the Lifeless with this powerful potion.", BigDecimal.valueOf(99.99), 60L, Set.of(potions), theNightwatcher),
                    new ProductEntity(null, "Allomancer's Guidebook", "Learn the basics of burning metals.", BigDecimal.valueOf(24.99), 100L, Set.of(books), hoidIndustries),
                    new ProductEntity(null, "Breath Pendant", "Carry extra Breath with this elegant pendant.", BigDecimal.valueOf(299.99), 40L, Set.of(homeGoods, potions), nightbloodGear),
                    new ProductEntity(null, "Shardplate Polish", "Keep your Shardplate shining and battle-ready.", BigDecimal.valueOf(29.99), 150L, Set.of(homeGoods), stormlightSurplus),
                    new ProductEntity(null, "Voidbringer Deterrent Kit", "Defend your home from Voidbringer invasions.", BigDecimal.valueOf(249.99), 80L, Set.of(homeGoods), worldhopperExpress),
                    new ProductEntity(null, "Chasmfiend Plush Toy", "An adorable plush version of the terrifying beast.", BigDecimal.valueOf(19.99), 300L, Set.of(pets), worldhopperExpress),
                    new ProductEntity(null, "Shardblade Keychain", "Carry a tiny Shardblade everywhere you go.", BigDecimal.valueOf(9.99), 500L, Set.of(homeGoods), mistbornWare),

                    new ProductEntity(null, "Pattern’s Puzzle Box", "Solve the puzzle and bond with a Cryptic spren.", BigDecimal.valueOf(59.99), 200L, Set.of(homeGoods), stormlightSurplus),
                    new ProductEntity(null, "Kaladin’s Emotional Support Windspren", "A windspren to keep you company during your dark moments.", BigDecimal.valueOf(14.99), 100L, Set.of(pets), hoidIndustries),
                    new ProductEntity(null, "Kelsier’s Cloak", "Join the Survivor's crew with this Mistborn-inspired cloak.", BigDecimal.valueOf(149.99), 75L, Set.of(clothing), kelsierCorp),
                    new ProductEntity(null, "Honorblade Pen", "Write like a Herald with this powerful pen.", BigDecimal.valueOf(29.99), 100L, Set.of(homeGoods), honorsHeralds),
                    new ProductEntity(null, "Scadrialian Metal Vials", "Carry your Allomantic metals in style.", BigDecimal.valueOf(19.99), 300L, Set.of(homeGoods, potions), scadrialSnacks),
                    new ProductEntity(null, "Adolin’s Stylish Boots", "Look fashionable while you duel.", BigDecimal.valueOf(179.99), 50L, Set.of(clothing), honorsHeralds),
                    new ProductEntity(null, "Nightwatcher's Curse Remedy", "Alleviate minor curses with this handy potion.", BigDecimal.valueOf(49.99), 100L, Set.of(potions), theNightwatcher),
                    new ProductEntity(null, "Shardhammer", "Perfect for both battle and construction.", BigDecimal.valueOf(499.99), 30L, Set.of(weapons), mistbornWare),
                    new ProductEntity(null, "Hoid’s Lightweaving Kit", "Create illusions just like Hoid.", BigDecimal.valueOf(299.99), 20L, Set.of(weapons, books), hoidIndustries),
                    new ProductEntity(null, "Highstorm-Resistant Tent", "Camp out during a highstorm without a worry.", BigDecimal.valueOf(299.99), 75L, Set.of(travel), worldhopperExpress)
            ));
        };
    }
}
