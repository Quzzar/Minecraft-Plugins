package quzzar.mod.Textures;

import java.util.UUID;

import quzzar.mod.Textures.Traits.BlockType;
import quzzar.mod.Textures.Traits.HUStrength;

public enum TextureDatabase{
	
	
	NONE ("4a7aedb2-926d-4a02-a9e2-212234779c59","http://textures.minecraft.net/texture/3228b6705059927723defc97b7cecd148b9b3a2d16f76a60c6e3ae4325996", BlockType.MISC,HUStrength.IMPOSSBLE),
	
	COAL_CHUNKS ("0aaf8952-c558-4e49-853d-48b773ca0d56","http://textures.minecraft.net/texture/42cf8ddb5268e18cec6daa3c506d4d2811bdba3f5ec42a68741cb1f342efa4a", BlockType.MISC,HUStrength.WOOD),
	IRON_CHUNKS ("726176ba-2f76-491f-a8fc-c471640736d1","http://textures.minecraft.net/texture/6db45f4a9bf23e5b48bf3e34a3c8d284074b0fcab7b847c7a7a18f5ef59e16", BlockType.MISC,HUStrength.STONE),
	GOLD_CHUNKS ("c2533415-9b18-448c-a9e9-c18aabd6bdc1","http://textures.minecraft.net/texture/310febddaa1acbaf2cecb496b4ccc4f1a65906e2286c771628e1dede160b299", BlockType.MISC,HUStrength.IRON),
	LAPIS_CHUNKS ("601bed82-9365-4568-acb5-6b45fcbcd1aa","http://textures.minecraft.net/texture/73421b9442d08aaae1421e8ea95d5ea0cc3b744349a11cae53dcb74ecd85", BlockType.MISC,HUStrength.STONE),
	REDSTONE_CHUNKS ("aed1b502-edcb-4d63-a76b-3b676cf36574","http://textures.minecraft.net/texture/1a673d34bea746e88f0bb5a2acf43f834b82febf6fc4c93fcb045bb572f64df", BlockType.MISC,HUStrength.IRON),
	EMERALD_CHUNKS ("07cd9c70-43ad-4be2-ad56-0776f359c7be","http://textures.minecraft.net/texture/9a667bb371a5f434ed477c7c1af0b9c75fd82d921574271c09032e43e8978db", BlockType.MISC,HUStrength.IRON),
	DIAMOND_CHUNKS ("cf7d1dbd-1b4f-417f-85ab-157a3f486329","http://textures.minecraft.net/texture/5b38cb50bcaf95a769befe95b44de646a2c2f5a691898044c5521df2c6aa7a5a", BlockType.MISC,HUStrength.IRON),
	
	BLACK ("f6049fcb-8469-4221-a615-f19ac88400cf","http://textures.minecraft.net/texture/af54a5fd40eeb5ee569fc427eb75a6e55646280e8325ea9208660268e10be9f", BlockType.MISC,HUStrength.IMPOSSBLE),
	MINICHEST ("419cc001-2999-4504-bbb6-49bdda633a3c","http://textures.minecraft.net/texture/49e1216ab039955cd15e70b638a3a36aed3a36e872ebbc027359a23eca9e3a4", BlockType.STORAGE,HUStrength.NONE),
	DIRTCHEST ("c9f9a097-3839-4ce7-b8e8-42064eae23bf","http://textures.minecraft.net/texture/7a76d5cdf1f719012199f1e2e8adfe8a4234a6623ef9b1b66b6ffdcee3e912c", BlockType.STORAGE,HUStrength.NONE),
	IRONCHEST ("56883ee0-c01b-42b5-9422-532937135f46","http://textures.minecraft.net/texture/4b5cc1c59a9499fdb7a12e284c6e47e4104f45323cf74e64384badeec4dde36", BlockType.STORAGE,HUStrength.STONE),
	QUARTZCHEST ("8ec6c191-7cca-4f70-8de0-78bc9267fb7c","http://textures.minecraft.net/texture/3c58bf4ae8342f16b074a63a731f34cc95da4eeb402b8066b2c979b932977f", BlockType.STORAGE,HUStrength.WOOD),
	GOLDCHEST ("1d0d1b76-c71f-4df5-927e-a99faccccfb5","http://textures.minecraft.net/texture/e0e110449d88e1d2ddc85c577be127fcba21d7f94c85283fb123ea97dd984228", BlockType.STORAGE,HUStrength.IRON),
	EMERALDCHEST ("6adce6cb-88e6-4b3f-9367-185fad75167c","http://textures.minecraft.net/texture/7d152328109260de617a4db558766f6f94b62c6e9612652c7b8313822d2eaea", BlockType.STORAGE,HUStrength.IRON),
	DIAMONDCHEST ("b3c3200c-727f-44ac-bd1b-4c4e2c3a6c98","http://textures.minecraft.net/texture/20c2efee5fd831e248424041f6e48346ef065c9352470a9e7716be9f074e34e", BlockType.STORAGE,HUStrength.IRON),
	InterDCHEST ("a8ff2489-cf42-4254-a09d-05c66597331b","http://textures.minecraft.net/texture/db462927ada5be35ed08c7062db8f2de93a26577b7542dbb7fddb55218561", BlockType.GLOBALSTORAGE,HUStrength.DIAMOND),
		
	BREAKER ("550cb24a-0673-4f6d-bebe-007878fb48e3","http://textures.minecraft.net/texture/432ebd96b13f3a2f6df7c9bb50f0dc313cd19d12acb5a15069164c27eb8ebc", BlockType.MACHINE,HUStrength.WOOD),
	PLACER ("4975d04e-0c22-4884-ae05-f0bffbdc7586","http://textures.minecraft.net/texture/cbc55cf2d0304fa4256b38c49cf7ef9ffd043d4e9ddf3269d7ac18c35f22ee9", BlockType.MACHINE,HUStrength.WOOD),
	
	COBBLESTONE_GENERATOR ("1ee7148c-9fcf-403a-b547-2e9666479f12","http://textures.minecraft.net/texture/9c0f71c0b8affa8d3931d3271ba7fede362da462f9b5d4baa4465f609813e", BlockType.MACHINE,HUStrength.STONE),
	
	RECIPE_VIEWER ("1f647589-43fa-466f-a8d1-60165966b8bf","http://textures.minecraft.net/texture/4ff36f6961b08846dab6ff59d60edb68267e6c4e284512d4fadfc732aba9b62", BlockType.MISC,HUStrength.NONE),
	
	AETHERIUM_CUBE ("453e7cfc-c975-4736-ad02-e9df2d6868db","http://textures.minecraft.net/texture/252d7477e5621b53b1b6ab9fe5ce8bc8334b748a50a956c35741e8fc1d14d", BlockType.MISC,HUStrength.NONE),
	
	L_DIRT_CHEST_item ("cc61631f-e995-4d0d-a2fd-693631448c9e","http://textures.minecraft.net/texture/64847e2ceb686cfbb799408ba0d957e4a53b82a17ed6181a62774747f15c8a4a", BlockType.MHU_Large_Dirt_Chest,HUStrength.NONE),
	
	L_DIRT_CHEST_FTL ("3b133ec5-721c-44a6-bc48-e51a3cf0c7e5","http://textures.minecraft.net/texture/604d5f7c2d10cbebe0a7afbc3b7dc1e6fed55b598c71478512f7cbdd0039bd", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_FTR ("c83138a6-a075-4c62-b592-80b160769534","http://textures.minecraft.net/texture/e43b8824256167b45bf7674c76484e6c5a9b83d226a242274b4f6736259934", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_FBL ("f2b050a2-cd91-49f1-92c8-7d5391637b61","http://textures.minecraft.net/texture/138e53da7c56ef17ed2f5be121479cd7c192f172f937c9c3c47e6c565dc8", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_FBR ("2d50afb4-aa68-479b-89e9-1d1637e69d7e","http://textures.minecraft.net/texture/2d7ba77fe610d27076c8ae98585a74d166be5b5d5220a1fee6c949964e7334d3", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_BTL ("93a7d1b9-9d77-40eb-ad44-de6c83a45517","http://textures.minecraft.net/texture/fdd4204766c4193eecd09a494ba22dde778744578c58f6a1941298296167", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_BTR ("b45d854f-4c4b-45a3-b033-3cec1c2f6fff","http://textures.minecraft.net/texture/54a4a8253b6b2fa67499dcb9b478ad6b701b87db2865951d922e68f546db", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_BBL ("1048d626-6fad-4eb3-8b20-dfff7cb5552c","http://textures.minecraft.net/texture/ec98f935c44159158f286f509e655d62eb5e391884011ad8bde68d3c9389d7", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	L_DIRT_CHEST_BBR ("ca54a99d-a423-4f38-8650-2883d1e40b40","http://textures.minecraft.net/texture/e494963ec58a987596eb2d902ff37bd92d334bbcc69ce7a81bb96e3819dac6d4", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	
	HEALER_item ("0cca9084-2436-4226-8186-c76f3c68dd83","http://textures.minecraft.net/texture/386bced293373789ce3bce4e916dc117cf544f27f6d6c9c66d9778a4345a24", BlockType.MHU_Healer,HUStrength.IRON),
	
	HEALER_FTL ("bef78c6b-f7aa-4dc0-9f86-1bb1caf9e50b","http://textures.minecraft.net/texture/a781a38d440e271246e5b7045c1bd29aaf4961677f2d39e2c3a6758db14a87", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_FTR ("8d605d0b-b08f-4d62-abb7-8360a5dab1b0","http://textures.minecraft.net/texture/8194f7a55fb55ea01b2594a6826ede42abb9d4ab6062fae23ef6aacaa9ee1068", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_FBL ("4b6c655b-37ee-43ac-a07d-cb32642eeaeb","http://textures.minecraft.net/texture/114c85373b132c8c2e60fd13a4d653ae93c24585b31f0f992c1685fcaa6653e", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_FBR ("4c8a3022-d6fe-441a-9bd1-c517461eb51b","http://textures.minecraft.net/texture/78cf8429ec3e61fbc6b5187925be92796a6ab86e878adcd1553f061794468", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_BTL ("df0ac378-33c6-4708-b5bb-f3875fa68e5a","http://textures.minecraft.net/texture/42fb395ee92f704887cda4157ddfa151fe134578cf76dd6fc99679d1693b97", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_BTR ("a7bcbdba-a1be-49c2-9535-456c4f76725a","http://textures.minecraft.net/texture/dacbe4f728fa69729a7d6e973023d4bb822f4e4efaf6564aa8f9cea9377", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_BBL ("13fdd54e-0975-4084-a979-bc30f8cab3b6","http://textures.minecraft.net/texture/4986807852225072a0fcc4b5a89555d9d563a9f5a51d68ae31fad984e8b084c4", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	HEALER_BBR ("3a68028e-ca94-40f4-971f-6f563edf30bc","http://textures.minecraft.net/texture/3d19fab27c341c2f0a456431a9bd2c71a8cff5a3664744466bad9f5231d79d", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	MINER_item ("5140c7e1-1a7b-4fa3-9309-790af185f0d6","http://textures.minecraft.net/texture/f2d6974a3d78cbac489ee956f71df0986183dd99dca40e841a4d39dd0d9ff", BlockType.MHU_Miner,HUStrength.IRON),
	
	MINER_FTL ("5beab58f-9255-4901-b2a5-71592df2eec0","http://textures.minecraft.net/texture/2e801d7682fe69a656285243217f2060dd2fbf50c4fabfd1e94e71cd9a1d2", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_FTR ("94483c33-e3e9-46d3-b4ab-adeef3e72225","http://textures.minecraft.net/texture/54def017805bf34cbbbabe8de3267ee18c76682b967e283afccc77245ce1a89", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_FBL ("b83e1272-dd88-4f3b-8721-274d3c79a358","http://textures.minecraft.net/texture/4fd99e71ba15df06e5bb5a73dddfc280f17a5f74234dc46536453e777e7aae", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_FBR ("66af5b6a-825f-4721-9f0f-8830e006bc56","http://textures.minecraft.net/texture/ce21ff48b4fa97d7ae0f2d81363a0bfcd2766739266c3361d4862b454548d9", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_BTL ("f366f078-409b-445e-b780-ccd686bc1e5f","http://textures.minecraft.net/texture/2a802278426b2c81cee4e7422639ea11144b667bb582cb5f8fe4375bf7f8d", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_BTR ("aab1ba95-8fda-4d5d-96fb-2758b4763861","http://textures.minecraft.net/texture/33b7d3ae3c92de8b77189de71cc91c1256a65229f2133441a2e643a31132ebc", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_BBL ("c3cd678d-4ddb-41b7-a2c4-cf34f51f729d","http://textures.minecraft.net/texture/6d3e4eca7ec54986a115ebeb82ec8ce418f0dfb889322f438a3674621353b8b7", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_BBR ("ff99a932-06b6-4e6e-a00f-d1567a5bce08","http://textures.minecraft.net/texture/e2c7445debe24b479e3da158e2b1b48f6d8e2120778abe638a406dc8789e9", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	MINER_ON_FTL ("2b150ec0-6851-4a4f-9b92-c573bc2ce74b","http://textures.minecraft.net/texture/3af620f8b9046ceb8c2aebee153dd5623f3274b62da6a8dea4fa84f4e3b61d", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_ON_FTR ("f14d8877-a4f6-4cf5-902c-049206394f51","http://textures.minecraft.net/texture/f2604bc5b6e83e796ac3f5ed645cc1e4dc9e979d3f0e1bdb83bf0abede19357", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_ON_FBL ("092fdbda-0ff7-49ba-9df4-9cc0c1f2d1a8","http://textures.minecraft.net/texture/7dbd69ad2d13c839079e5dbd937258c24ec7cf868beb4be77de362fee462780", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MINER_ON_FBR ("cb807ddc-e983-42e9-b02c-857e0a397432","http://textures.minecraft.net/texture/6992419174efc8e2fe5d2d5719415c9e38ac4683747636b998a5c2d314ecde", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	COMPRESSED_ANDESITE ("1d9adc5f-fe6c-4c87-94c9-93403aa61228","http://textures.minecraft.net/texture/45ab5f19c9e8a793ea3ea12962c74aa69ff2b938c92465ff8d65e951b47604d", BlockType.MISC,HUStrength.WOOD),
	COMPRESSED_GRANITE ("9f1672f5-04ea-405f-953e-9287664409bf","http://textures.minecraft.net/texture/c6cbb18748fc9c61a4809c99201ec8ec26c5233d946882f9f51eb63eb0e628", BlockType.MISC,HUStrength.WOOD),
	COMPRESSED_DIORITE ("cd54c500-7cc8-442c-b56a-2e02f3bbb667","http://textures.minecraft.net/texture/939a5c118a9ce4dc0f83b9d9c763b22e11c7f9b3fdf47512f44e49062d121fb", BlockType.MISC,HUStrength.WOOD),
	COMPRESSED_DIRT ("b3b4a22c-54b1-420d-b797-32758cdd5ed9","http://textures.minecraft.net/texture/067cff1fec89479aafd7621f165e08335937132621e93d948e982bb197e54b4", BlockType.MISC,HUStrength.NONE),
	COMPRESSED_NETHERRACK ("636df51f-321c-4a1f-8101-1ba50068646a","http://textures.minecraft.net/texture/2784b06c5535cf9355f0979ae31eec248f9193e470df94c3559c6d2973413", BlockType.MISC,HUStrength.WOOD),
	COMPRESSED_COBBLE ("efcbff1a-bbb5-42d7-83fa-ea995ff76c8b","http://textures.minecraft.net/texture/bf14f8d7a8d77b86dd4cd3e72da6d1fcc89a2ea8063b1b534f1fbbbac2ff48", BlockType.MISC,HUStrength.WOOD),
	
	EXTREMELY_COMPRESSED_COBBLE ("585ecd54-a239-4966-8546-276595962538","http://textures.minecraft.net/texture/d07a2b9fe09b12f7fe325c841fa6317243f5e5b3abf6aa35f5dd6f302218fe82", BlockType.MISC,HUStrength.STONE),
	INSANELY_COMPRESSED_COBBLE ("b9dcfbb3-8c0b-4083-8bcc-4fb38ba3a1d0","http://textures.minecraft.net/texture/388d3648181fe144f907fa05e5856df24b8b6c9c634eb52a9b414adea7e", BlockType.MISC,HUStrength.IRON),
	
	THE_DARK_GATEWAY ("da4bac85-ae5a-4704-abe5-efdf70d4d7f4","http://textures.minecraft.net/texture/6b3bdc9d64c27b4c2563548a9235f59e9189bc113c2764c06d88d6269ef29b", BlockType.MISC,HUStrength.DIAMOND),
	
	
	WOOD_CRATE_COMMON ("13a3a9ec-188c-4906-9ca0-955042d67726","http://textures.minecraft.net/texture/2f0c92ed66139ee3f7eeb2a93f6a27ff5c758704fd3ad215cdade4253393b", BlockType.CRATE,HUStrength.NONE),
	WOOD_CRATE_UNCOMMON ("1b6f2e27-82fc-4623-9a85-07e4d22d3555","http://textures.minecraft.net/texture/2f0c92ed66139ee3f7eeb2a93f6a27ff5c758704fd3ad215cdade4253393b", BlockType.CRATE,HUStrength.NONE),
	WOOD_CRATE_RARE ("0f3724c9-f5ed-4cb6-9cf3-616dae12eb82","http://textures.minecraft.net/texture/2f0c92ed66139ee3f7eeb2a93f6a27ff5c758704fd3ad215cdade4253393b", BlockType.CRATE,HUStrength.NONE),
	WOOD_CRATE_EMPTY ("be88600f-127e-47d5-b255-19ec198cef43","http://textures.minecraft.net/texture/e3909f6dc05fa6391f27c62973af699eb1b37a3eb9a09baa80bacbc84b252", BlockType.CRATE,HUStrength.NONE),
	
	RECYCLER_item ("691d0cbf-adef-4395-8b13-03b4c0b51186","http://textures.minecraft.net/texture/2c3c4dfa908da9a3d762516e49ae9773dc2fb5cf8c565835c471abaf439db97", BlockType.MHU_Recycler,HUStrength.IRON),
	
	RECYCLER_FTL ("f0f1ab0e-5124-49e8-b012-ce22c4526d95","http://textures.minecraft.net/texture/57c19ac5c4cc954d555e7a94ddb5ecdb6e6acdc161ec596ae2f0fe9e69d5f9ad", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_FTR ("508c31bc-90da-4567-bfc6-b646ebb93ebe","http://textures.minecraft.net/texture/86aeffb8afc8a7ca1d6d81424230cf9c7918164134ebe7df11482c1933eb23", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_FBL ("9f9cd4a0-74af-4042-a2f8-59b741837fea","http://textures.minecraft.net/texture/13f6aad32067702bc883a5b2da1cf876f99f02a9b95dbe13daf1ee5bdb44691", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_FBR ("ab6088ff-a24f-41de-80d3-06545b43d8a1","http://textures.minecraft.net/texture/83d71d415041b8b2606558d32c691b9f5237e54ab13ba93d612b2f798bb5db", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_BTL ("625f5b10-7b95-4f81-8654-c0d6b122cb03","http://textures.minecraft.net/texture/4f5083f4dd657db2c92514add202ecb9f2adc2d2ad8f3c346dfbaad8f923fd6", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_BTR ("6fd6a825-1b3f-4454-999e-35acc9ea2c3f","http://textures.minecraft.net/texture/67ab907b8eeb3bb88d5335bfe0c38c32d7b34a9130683fa6b694958e4ee85e4", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_BBL ("a81e622b-641c-47c2-8f80-c6a777038056","http://textures.minecraft.net/texture/8f4d77996e1d667db549c99dcc04c862ec3cfa259f32d6012d21cfc61974979", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_BBR ("601803c1-0bd8-46e0-a266-ec3bd7fe4c83","http://textures.minecraft.net/texture/a1ec74877f14d3fbacf13f4d1e91cedce72e1e16ee3a5d94424f338946c9", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	RECYCLER_ON_FTL ("52f55b07-9249-4366-af18-0016baeecb64","http://textures.minecraft.net/texture/464da8f886c967f9c5e417f4d68ce0bcd935bbdd146a903fe7ae5123fb6", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_ON_FTR ("d5484548-6dbe-40d8-b1b9-94f19368424d","http://textures.minecraft.net/texture/a6bd33c32a8c72113558cac2436723a219410cb48402d796023e1e6bea0e15b", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_ON_FBL ("4a0388d5-d741-409e-9862-c2c6f00c4b71","http://textures.minecraft.net/texture/e0b713568b916267e6d9df646ffc414a45b1c3c6e4a6525cf598ee86f9f8a73", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_ON_FBR ("7e3bfd3e-bc1a-4779-a8a9-1c94c0b88b04","http://textures.minecraft.net/texture/8264b628c6873beb86e6ee154c76d390c2b593f234aa7bc1b3b55966fa8", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_ON_BTL ("150564ef-a0e6-4784-b86a-5e53d6cdc5be","http://textures.minecraft.net/texture/6c245cfcaf32e036ef1273cea41ea0f622a187aaf15527fedd0dd1b527debee", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	RECYCLER_ON_BTR ("8a3e61f4-4550-46cc-ba57-bcfdc163dc31","http://textures.minecraft.net/texture/eb682fd967c67fb8c8f5dee251b5de7f21be7c14c28ab737543457424a0f1", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	
	MACERATOR_item ("e7ee4e3c-f977-4741-9f21-0b616e56d2f9","http://textures.minecraft.net/texture/a3f5dc6330cb3d8b5f33543a7b32f264795c5f18d681d54362aa43981256cf", BlockType.MHU_Macerator,HUStrength.IRON),
	
	MACERATOR_FTL ("36f16698-c676-4d31-b8b5-ed2a269724ff","http://textures.minecraft.net/texture/d775333afa99dd6955d99ec1c5da58cad684aea398c248fdf16ed52a3ec29c", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_FTR ("5dbff9e3-3a86-44e3-9f2d-b6d624bcfcf1","http://textures.minecraft.net/texture/f5d482b9d2f853c79624d8d18681d154a38bc3abc64327fbf35f6e424824a1", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_FBL ("837fc015-784a-432c-b9b1-5915910bb82d","http://textures.minecraft.net/texture/a8282a9b3892b6f5e252994b082409f7aad618e92fec99c47b674ab4e9c7", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_FBR ("24b78de3-04eb-4962-8176-f5f07b519ef1","http://textures.minecraft.net/texture/ec3020269ed7371518fbb6f2be45290b29f43cdb597a033fb58c925c9e2b8d6", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_BTL ("9e569157-3668-4090-9c6c-a86f2b6aa7ca","http://textures.minecraft.net/texture/c152ea322a8c7bdc1dd6179296412b46bc9032ad6c9f64462943c752250", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_BTR ("502d5782-2d44-4c5c-94c4-5ca74565bd47","http://textures.minecraft.net/texture/1053c651de5629c6b260ee8e667e07c8128cb452ce4daa434d879cf64ad5950", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_BBL ("a63e8566-69e2-4c72-9821-594e74e741c9","http://textures.minecraft.net/texture/071e5e050ca42f95998a848c7539e97e4fe8656f1db74641461fb0aa9563c4", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_BBR ("ae0e4809-101b-45fd-94ad-064a2e8ee6d6","http://textures.minecraft.net/texture/c48dad5a6a88ef9337f5748aee12d25cdd39242d404d7d3d1bba546daad", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	MACERATOR_ON_FTL ("634a7dd9-fc98-462e-b3ab-a02fdf30cc26","http://textures.minecraft.net/texture/cae134c820bfd0769d1fda9ce4f27291d2a773a18921d519061f1d91a4ac1d3", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_ON_FTR ("eb28d6e9-414e-4104-bf74-74c349a8d249","http://textures.minecraft.net/texture/4dc077689fce22932cabdeb963eb733221eca5cc0cb5cb010b0fc3e8854895a", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_ON_FBL ("f701c1be-5158-4f8c-bbcf-38dd084d50e1","http://textures.minecraft.net/texture/5ed3c9b1e3f5ef78b3131051a713679e789d3a2e3345be21207f9362b8d1936a", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_ON_FBR ("f391a979-e769-4f27-8b63-c9e522412644","http://textures.minecraft.net/texture/843dd549b87ed15a618073a2a6e72b876d3caa86eb41aa791cb23cdb6ceffd1b", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_ON_BTR ("f1573828-609d-4470-8e88-01f80caf3a8e","http://textures.minecraft.net/texture/b111464b43b8254ebccb42a7893325558781577d51272b9541723a8787308020", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	MACERATOR_ON_BBR ("04cc38f7-94fd-4edb-983a-fedd44c6e07b","http://textures.minecraft.net/texture/1ba28411cf5be950d3c25aa9f3e9cdec25b3b7b5e2c02a4f258aae79bb540cd", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	
	AUTO_CRAFTING_TABLE_item ("02b104dc-4401-4579-b2f5-f12bb1f15d84","http://textures.minecraft.net/texture/13a81cbfbc13154c2a31272e55a7f2334c6754ce24c39e2383a37b7f26bd2c", BlockType.MHU_Auto_Crafting_Table,HUStrength.IRON),
	
	AUTO_CRAFTING_TABLE_FTL ("0a1d6823-eca7-46e2-958d-b70c28b4939b","http://textures.minecraft.net/texture/2258f8fa6baac94eaa61652021b2e795b3142886ac791304a7d991a3fcb4a7", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_FTR ("2271e103-1070-4a53-a636-913ee4aa725c","http://textures.minecraft.net/texture/b71ba608e62ff8d8a998c7d8875f9cef28f1df103be37b028bb74d4eefe77", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_FBL ("0c8b0a5b-8cf9-4dd8-80d2-01651214fa6c","http://textures.minecraft.net/texture/eca59dabff87a6e58f47fc6915543eecc85d2d446f346396d9707c6574d689af", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_FBR ("12284b9b-6ba5-4bc1-ae5f-ae417e01f0a2","http://textures.minecraft.net/texture/c7533ad8bc2073b9e4925ab1acf7f32e6463603f89723a261ef96f6b3538b26", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_BTL ("9c3388f1-ab92-4414-b717-1d6633ff5a00","http://textures.minecraft.net/texture/795c295c38bc766b2b53385b1919315e5d5fdce94c9c94b3627e4f839a8dcae", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_BTR ("b392d124-8ee6-4589-923b-771f2ea10623","http://textures.minecraft.net/texture/51a2164d2ea666a786bed8f4c0a0dd3a34285df02092168d1f45318f5cb2cac", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_BBL ("86003a48-0742-4eff-8508-f77a23015875","http://textures.minecraft.net/texture/6ed41b75b89ef5c9533f8b686a2b3b94153e3b7cf8ca7e9baebdc43447", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_BBR ("90fe8368-2540-4faa-8686-e9f95a507616","http://textures.minecraft.net/texture/5b8a5a3a3584cfd07dfcc68b54d354a7af1507fa3dc2b4abefd7b4253cf836", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	AUTO_CRAFTING_TABLE_ON_FTL ("90476d31-418d-4cde-9ae6-929ff2e4f691","http://textures.minecraft.net/texture/359affee9a377915e461b13cbc901ed0ab639c364ba4edff947bdd1728d73790", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_FTR ("623dff53-0dda-44c9-a719-35b68f256f29","http://textures.minecraft.net/texture/747eb5597d9af6c2dfa15da5b4e1b584efd7e27c9c6626974ec2e5f1856e2e", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_FBL ("506a825a-e334-4207-a157-c20407eb7238","http://textures.minecraft.net/texture/193eaaebba5232438e729ba3a6352aae450204d77de05448689b23c1d6934", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_FBR ("16f2e706-f5b5-4cc8-822b-6bc0845d2cca","http://textures.minecraft.net/texture/468eaa60d1e528e77929e6487e3f6408a2e92fd40721fca73fe854540cd", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_BTL ("4dc4e9cd-388c-4a8b-9f98-bff8f1a14fe8","http://textures.minecraft.net/texture/e7565c6f937cf99e6e60d3f51ebd4a4a83739416b69842b55a7edfd4705a15", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_BTR ("b6f12af5-c1ff-42a4-9aa6-748861597ec6","http://textures.minecraft.net/texture/d489412e94eeb839fb11a49705bbcea846ae08c2b123d31248c49381ca93", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_BBL ("d7b7f290-39a8-4ae9-89b4-a5850407441b","http://textures.minecraft.net/texture/554f88eb5a9facab8e2eafc6751d1a584acb53e88c2497d2f88a315abca350", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	AUTO_CRAFTING_TABLE_ON_BBR ("a4a8c8bb-1594-4315-b228-7cec74a40dca","http://textures.minecraft.net/texture/8c7b7e9b78cfa0b3435f3919ef48bb32503e3bcea7d138852e4abec29fd677f", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	
	INDUSTRIAL_FURNACE_item ("bb62c3ff-a9d2-42c6-9a71-97d420463a9d","http://textures.minecraft.net/texture/ad8aad5bef6c21485962aa70282242a27e14b19d5a1cbe2c4f3cfe3df3dc5b11", BlockType.MHU_Industrial_Furance,HUStrength.IRON),
	
	INDUSTRIAL_FURNACE_FTL ("9d0efef5-7cc5-40e2-978e-8f896946edfc","http://textures.minecraft.net/texture/adf08995e6c41415fb159f1c5c647bb89db713807e2f688a30269956956d9473", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_FTR ("288853c0-656f-4cad-b244-228a70c08e2d","http://textures.minecraft.net/texture/9f25b7776cfd65b5431291cc2fa9a91efc2fc3563a5157287549d6d3a3e392d", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_FBL ("9cb78fd6-08df-4db4-90bd-325f1d223e9f","http://textures.minecraft.net/texture/ecd9d288fa55e2a47224a586c70d3b51c1cfd48019abce656faad19dca42d", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_FBR ("cf41029f-6a59-4068-8080-52c904c4d253","http://textures.minecraft.net/texture/723441f9821ac173ef3d47651f822cc5868a6f179994a35f5e54e17f7a55b", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_BTL ("bbc7438f-06de-4ae1-b835-e20754b5031a","http://textures.minecraft.net/texture/ae1c878cbbbf7a0fd5c76c4fb27677e95c43862e27cfa4b780ea3ace86e8", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_BTR ("9abf48f7-c9c7-4abe-b258-01e82be09a6b","http://textures.minecraft.net/texture/5e652a29a647cb3bdb327dc68b5cd4a903b39d96026cf324b4ef7586587c7", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_BBL ("affc23fc-f1db-4edc-a788-f8f49b83d9c1","http://textures.minecraft.net/texture/c09d37613ceeeeed517955fca8af8ddafeca3ce2aa9515857a463477e8ef1cc", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_BBR ("fa16dc5d-4497-44a3-9022-2c86f4fa3346","http://textures.minecraft.net/texture/3cb180b632e49db8562f82d4537d1e15d52dd3126198cd439f21b8dd9ef518d3", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	INDUSTRIAL_FURNACE_ON_FBL ("844d41e1-44e0-4432-ac5f-13574eb5795d","http://textures.minecraft.net/texture/b4bd7cfdde38655e241d9e3f3846803244a08b4876a2b15a9c97e5c2d72ecc", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	INDUSTRIAL_FURNACE_ON_FBR ("2ecf6ea0-1b1e-4a45-9e8f-39fe122d0c4e","http://textures.minecraft.net/texture/3bf3c77496af2b55160e469a7d9bbe71bac5cfbe29dd87b3aab896cf6cbddeb", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	
	PIPE_item ("918f13cc-e920-4de2-9d14-a898af5ad871","http://textures.minecraft.net/texture/c429762d81221d2689556beb64bc2d8e9c98e3d6640378d47c9a2bb93c59ff", BlockType.MHU_Pipe,HUStrength.STONE),
	PIPE ("239ee26e-ce72-47c4-bc65-2cf472000b82","http://textures.minecraft.net/texture/8a3fffa99f43c866badc21f5d86392f71e631f52d4db416e187ed574cc68498", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	PIPE_END ("855e21e3-b79f-405c-a3ef-cabacd9c31c4","http://textures.minecraft.net/texture/48379a2447f591824cae4a9bfb6c3369e9b98e20ab461da3ddda8b41387e9b2", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	GRINDING_UNIT ("c638d646-55a8-4aaa-b2d5-88e8acc04419","http://textures.minecraft.net/texture/159e69b73827e646233270cbdc466d5e26317488c19f9a08fa82ed5b25884", BlockType.MISC,HUStrength.STONE),
	
	CRAFTING_TABLE ("b5a22b0f-6709-4e92-ab90-a9410313f476","http://textures.minecraft.net/texture/7ead389269c04feba791de17c5a7953c41c2cb3663b1346819b3c2ced31b2", BlockType.MISC,HUStrength.NONE),
	
	ASSEMBLING_COMPONENT ("e751f6e3-90e8-45a9-b6a2-635b12de227e","http://textures.minecraft.net/texture/ad339cdec8371f62a29b7aa7e4ef3edc791bbf07c43a192eff5b5231a7d958", BlockType.MISC,HUStrength.STONE),
	
	
	SPEED_UPGRADE ("48a97f63-d615-468f-b632-004c9d6ee340","http://textures.minecraft.net/texture/52debef350738aa65959e2b1de884e4b9b60709568a108ce19c1f6ddbb891", BlockType.MISC,HUStrength.IRON),
	STORAGE_UPGRADE ("8180c403-6ed5-40f7-b229-a5799ff1d958","http://textures.minecraft.net/texture/9fcb92b82182287dde2985f1864b16db232eedbcb609a1b949f301b236639d", BlockType.MISC,HUStrength.STONE),
	
	
	ITEM_FILTER_item ("2a5d7346-ca6d-4d6b-a39e-98dbad57c47f","http://textures.minecraft.net/texture/ada8b8fc546e1eb27f21ddec5535d6c5e3cf9b8a99da59184cc83e7d27b6c2f", BlockType.MHU_Item_Filter,HUStrength.IRON),
	ITEM_FILTER_BASE ("86e3999d-a472-4267-b54f-d374df28d869","http://textures.minecraft.net/texture/a49fa83188ce3e376aaac3c7beed7e37f492947ad3584d65824604237fe29", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	ITEM_FILTER_END ("691f1612-2b6d-4b8e-bec3-f8049414ccb4","http://textures.minecraft.net/texture/29b747f6f7921df5c8c1295ae68c5625e16d56ad3dbbf39f32942f40c59", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	
	INCINERATOR_item ("34c346dd-e105-47f4-a4cc-b1664a077c91","http://textures.minecraft.net/texture/0183eba2eaf66dade8a6aa16937c564ba375e9719dd18669bce25e91b642b1", BlockType.MHU_Incinerator,HUStrength.STONE),
	INCINERATOR_BASE ("88d16404-04a3-4684-9cae-0ab69e3084bb","http://textures.minecraft.net/texture/8fe81ab2eac96b89ddf5478badf6d1d3b6856cad7fc2a4127382594585947a0", BlockType.STORAGE,HUStrength.IMPOSSBLE),
	
	
	QUARRY_item ("e7cab42c-69de-4ca6-b7ad-11c629c77ccf","http://textures.minecraft.net/texture/f5894a7fbdb41f6ea91496ceaad029a3c060431f686ebd8c934588bf6e414f", BlockType.MHU_Quarry,HUStrength.DIAMOND),
	
	QUARRY_FTL ("450298ba-b85c-4a58-a10f-37ff6c3a9dbb","http://textures.minecraft.net/texture/374659acc67c8fb177ea848bab26e937c26efc5d9ac51c638db175158f74691", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_FTR ("cdf7d6ab-50c0-41fc-b85f-3e1934ebad3d","http://textures.minecraft.net/texture/57317c31de6e6c8eccbb98982dc3dce76c8ab8339cfe14d343d0b3c94d728", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_FBL ("1833948a-af30-4189-a0c5-1099bba5dc6a","http://textures.minecraft.net/texture/3ceb9459bf61261cc4baa5e3944a2524d048654272fb14d2afd17ce861cc38e1", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_FBR ("3073393a-4736-49ff-9468-44c943d6a156","http://textures.minecraft.net/texture/2a177a2534b1fe9a01ea45cb6cf1d4fca40f55e9b8e1fb8758145f231ffc6d5", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_BTL ("1ff31eb1-571d-41ee-b8f0-faf4e927b610","http://textures.minecraft.net/texture/dfe3aa5e12a5854baa7a588cf27d16f3a53da015236f6b8287f29c31b297f", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_BTR ("b86e05b9-94b8-45c3-a6f0-cb3fc37901a9","http://textures.minecraft.net/texture/2ebfb75569288a3977cf1c2d8285ed10ca486364fcc194522bd188de3cf8e68", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_BBL ("ca1309d0-b735-41b3-8fe5-557d12baecd8","http://textures.minecraft.net/texture/f2bbc896f62da77de686449e2e8a8d9ac4a6db1ca24c7c6f79d65e276ca", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_BBR ("84f92466-5339-49ce-992c-60e33363f477","http://textures.minecraft.net/texture/2cddbbcd624ae9d7411384a3ab2b83db6896f5442da46d6eefd26051ac272b70", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	QUARRY_ON_FTL ("e4cee7ba-b0d8-489c-b1ee-7d881a291757","http://textures.minecraft.net/texture/20dac669d50a621734e5748a48023a7272f738e8cbac772d2cb1274ae3abc45", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_FTR ("03a590fa-4feb-4185-a2ba-c319f0cbf845","http://textures.minecraft.net/texture/1aaaec064d6a7c53bb65c7a977a2f9e68f3fa279c4688ef80c32b3eac909c9e", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_FBL ("0643e11a-738e-4943-a711-4bd241202801","http://textures.minecraft.net/texture/9250a04444eeff6551ebfe1741719a5ae51d5b063d8aa2aa53ed84f18d32d8", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_FBR ("71e8fa29-40bc-4f33-b437-1fdc1d1c1124","http://textures.minecraft.net/texture/c0791a2c45dad9e95f43d1f9cd49fcb223fee338cfa9817e25bd0d1a1f3f849", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_BTL ("bb0b3dca-7e68-45ff-87f5-bf9866c2d523","http://textures.minecraft.net/texture/1986d8793e294b8aa66a43e89bde25c65b612dafb383707fd78effed559f4", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_BTR ("263e791a-4f72-45f9-a1cf-e136e9cf1f4b","http://textures.minecraft.net/texture/3e9cf1152069704975df4accfd32281a37abd1845565d4d1ded98935e8fdb", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_BBL ("9bad66fd-22cc-4835-a83e-b891d86218c0","http://textures.minecraft.net/texture/6622de16836277bfc4f5d14345c25145706c87546d8e472a783915633548317", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	QUARRY_ON_BBR ("d62548e5-795f-414d-9ec3-4541c060c45e","http://textures.minecraft.net/texture/cd96f429f1298d7dee620aba4fbec107f478bc95e11131442bdd756789c23", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	
	
	CREMATOR_item ("a695db46-4208-44cb-b1eb-16513a083ec7","http://textures.minecraft.net/texture/12b1956b4a7c9682f5f8f521817fc32136cae861e3267f16a575e32b1e42f", BlockType.MHU_Cremator,HUStrength.IRON),
	
	CREMATOR ("48b5dd73-e08d-44a1-8acf-2d68c1210f59","http://textures.minecraft.net/texture/b150271aaf9db0c3e303522395c6dd34cfe9b7fa67f4e1387eebce53e44308d", BlockType.MACHINE,HUStrength.IMPOSSBLE),
	CREMATOR_ON ("f70cb8d2-8b53-4a43-b3e0-ce1e2e5f28be","http://textures.minecraft.net/texture/9baba242c9298e3b6b85ebf5fafbeee3ef2b63d4b0f6bdbb758ba42c2895c9e", BlockType.MACHINE,HUStrength.IMPOSSBLE);
	
	private final String url;
	private final HUStrength str;
	private final BlockType type;
	private UUID uuid;
	
	TextureDatabase(String rawUUID, String url, BlockType type, HUStrength str){
		this.url = url;
		this.type = type;
		this.str = str;
		this.uuid = UUID.fromString(rawUUID);
	}
	
	public String getURL(){
		return url;
	}
	
	public UUID getUUID(){
		return uuid;
	}
	
	public BlockType getType(){
		return type;
	}
	
	public HUStrength getStrength(){
		return str;
	}
	
	public static TextureDatabase getTexture(UUID inputUUID){
		for(TextureDatabase tex : TextureDatabase.values()){
			if(tex.getUUID().equals(inputUUID)){
				return tex;
			}
		}
		return null;
	}
	
	public static TextureDatabase getTexture(String inputName){
		for(TextureDatabase tex : TextureDatabase.values()){
			if(tex.name().equalsIgnoreCase(inputName)){
				return tex;
			}
		}
		return null;
	}
	
	
	
	
}