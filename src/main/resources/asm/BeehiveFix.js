var asmapi = Java.type('net.minecraftforge.coremod.api.ASMAPI')

function initializeCoreMod() {
    return {
    	'BeehiveFix': {
    		'target': {
    			'type': 'CLASS',
    			'name': 'net.minecraft.block.BeehiveBlock'
    		},
    		'transformer': function(classNode) {
    			if (classNode.superName == "net/minecraft/block/ContainerBlock") {
    				classNode.superName = "com/lupicus/bk/block/RotateContainerBase"
    				for (var i = 0; i < classNode.methods.size(); ++i) {
    					var obj = classNode.methods.get(i)
    					if (obj.name == "<init>") {
    						fixSuperCall(obj)
    					}
    				}
    			}
    			else
    				asmapi.log("WARN", "BeehiveBlock might not rotate (" + classNode.superName + ")")
    			return classNode;
    		}
    	}
    }
}

function fixSuperCall(obj) {
	var call = asmapi.findFirstMethodCall(obj, asmapi.MethodType.SPECIAL, "net/minecraft/block/ContainerBlock", "<init>", "(Lnet/minecraft/block/Block$Properties;)V")
	if (call != null)
		call.owner = "com/lupicus/bk/block/RotateContainerBase"
}
